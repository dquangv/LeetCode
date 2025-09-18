class TaskManager:

    def __init__(self, tasks: List[List[int]]):
        # Priority queue (min-heap), but we store (-priority, -taskId, userId)
        # Why negative? -> Python heapq is min-heap, we want max-heap
        self.tasks = [
            (-priority, -taskId, userId) for userId, taskId, priority in tasks
        ]
        heapify(self.tasks)

        # Map taskId -> priority
        self.prs = {tid: pr for _, tid, pr in tasks}
        # Map taskId -> userId
        self.usrs = {tid: uid for uid, tid, _ in tasks}

    def add(self, userId: int, taskId: int, priority: int) -> None:
        # Push task into heap
        heappush(self.tasks, (-priority, -taskId, userId))
        # Store mappings
        self.prs[taskId] = priority
        self.usrs[taskId] = userId

    def edit(self, taskId: int, newPriority: int) -> None:
        # Update if changed
        if newPriority != self.prs[taskId]:
            self.prs[taskId] = newPriority
            # Push updated task into heap (lazy deletion strategy)
            heappush(self.tasks, (-newPriority, -taskId, self.usrs[taskId]))

    def rmv(self, taskId: int) -> None:
        # Invalidate task by marking userId and priority as -1
        # (Lazy deletion, won't remove immediately from heap)
        self.prs[taskId] = -1
        self.usrs[taskId] = -1

    def execTop(self) -> int:
        if not self.tasks:
            return -1

        # Pop top element (highest priority, tie by highest taskId)
        pr, tid, uid = heappop(self.tasks)
        pr, tid = -pr, -tid

        # Lazy delete loop: discard invalid or outdated heap entries
        while pr != self.prs[tid] or uid != self.usrs[tid]:
            if not self.tasks:
                return -1
            pr, tid, uid = heappop(self.tasks)
            pr, tid = -pr, -tid

        # Remove from maps
        self.rmv(tid)

        # Return userId of executed task
        return uid
        
# Your TaskManager object will be instantiated and called as such:
# obj = TaskManager(tasks)
# obj.add(userId,taskId,priority)
# obj.edit(taskId,newPriority)
# obj.rmv(taskId)
# param_4 = obj.execTop()