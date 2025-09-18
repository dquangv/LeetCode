class TaskManager {
    // Inner class to represent a task
    private static class Task {
        int priority;
        int taskId;
        int userId;

        Task(int priority, int taskId, int userId) {
            this.priority = priority;
            this.taskId = taskId;
            this.userId = userId;
        }
    }

    // Max-heap with custom comparator:
    // - Higher priority first
    // - If tie, higher taskId first
    private PriorityQueue<Task> heap;
    private Map<Integer, Integer> prs;   // taskId -> priority
    private Map<Integer, Integer> usrs;  // taskId -> userId

    public TaskManager(List<List<Integer>> tasks) {
        heap = new PriorityQueue<>((a, b) -> {
            if (a.priority != b.priority) return b.priority - a.priority;
            return b.taskId - a.taskId;
        });

        prs = new HashMap<>();
        usrs = new HashMap<>();

        for (List<Integer> t : tasks) {
            int userId = t.get(0);
            int taskId = t.get(1);
            int priority = t.get(2);

            heap.add(new Task(priority, taskId, userId));
            prs.put(taskId, priority);
            usrs.put(taskId, userId);
        }
    }

    public void add(int userId, int taskId, int priority) {
        heap.add(new Task(priority, taskId, userId));
        prs.put(taskId, priority);
        usrs.put(taskId, userId);
    }

    public void edit(int taskId, int newPriority) {
        if (prs.get(taskId) != newPriority) {
            prs.put(taskId, newPriority);
            heap.add(new Task(newPriority, taskId, usrs.get(taskId)));
        }
    }

    public void rmv(int taskId) {
        prs.put(taskId, -1);
        usrs.put(taskId, -1);
    }

    public int execTop() {
        while (!heap.isEmpty()) {
            Task top = heap.poll();
            int taskId = top.taskId;
            int priority = top.priority;
            int userId = top.userId;

            // Check validity
            if (prs.get(taskId) == priority && usrs.get(taskId) == userId) {
                rmv(taskId);
                return userId;
            }
        }

        return -1;
    }
}

/**
 * Your TaskManager object will be instantiated and called as such:
 * TaskManager obj = new TaskManager(tasks);
 * obj.add(userId,taskId,priority);
 * obj.edit(taskId,newPriority);
 * obj.rmv(taskId);
 * int param_4 = obj.execTop();
 */