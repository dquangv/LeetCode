// Task struct holds priority, taskId, and userId
type Task struct {
	priority int
	taskId   int
	userId   int
}

// MaxHeap type implements heap.Interface
type MaxHeap []Task

func (h MaxHeap) Len() int { return len(h) }

func (h MaxHeap) Less(i, j int) bool {
	// Higher priority first, if tie then higher taskId first
	if h[i].priority == h[j].priority {
		return h[i].taskId > h[j].taskId
	}

	return h[i].priority > h[j].priority
}

func (h MaxHeap) Swap(i, j int) { h[i], h[j] = h[j], h[i] }

func (h *MaxHeap) Push(x interface{}) {
	*h = append(*h, x.(Task))
}

func (h *MaxHeap) Pop() interface{} {
	old := *h
	n := len(old)
	x := old[n-1]
	*h = old[:n-1]
	return x
}

// TaskManager struct
type TaskManager struct {
	h     MaxHeap
	prs   map[int]int // taskId -> priority
	usrs  map[int]int // taskId -> userId
}

// Constructor
func Constructor(tasks [][]int) TaskManager {
	h := MaxHeap{}
	prs := make(map[int]int)
	usrs := make(map[int]int)

	for _, t := range tasks {
		userId, taskId, priority := t[0], t[1], t[2]
		heap.Push(&h, Task{priority, taskId, userId})
		prs[taskId] = priority
		usrs[taskId] = userId
	}

	heap.Init(&h)

	return TaskManager{h, prs, usrs}
}

// Add task
func (this *TaskManager) Add(userId int, taskId int, priority int) {
	heap.Push(&this.h, Task{priority, taskId, userId})
	this.prs[taskId] = priority
	this.usrs[taskId] = userId
}

// Edit task priority
func (this *TaskManager) Edit(taskId int, newPriority int) {
	if this.prs[taskId] != newPriority {
		this.prs[taskId] = newPriority
		heap.Push(&this.h, Task{newPriority, taskId, this.usrs[taskId]})
	}
}

// Remove task (lazy deletion)
func (this *TaskManager) Rmv(taskId int) {
	this.prs[taskId] = -1
	this.usrs[taskId] = -1
}

// Execute the top task
func (this *TaskManager) ExecTop() int {
	for this.h.Len() > 0 {
		top := heap.Pop(&this.h).(Task)
		if this.prs[top.taskId] == top.priority && this.usrs[top.taskId] == top.userId {
			this.Rmv(top.taskId)

			return top.userId
		}
	}

	return -1
}

/**
 * Your TaskManager object will be instantiated and called as such:
 * obj := Constructor(tasks);
 * obj.Add(userId,taskId,priority);
 * obj.Edit(taskId,newPriority);
 * obj.Rmv(taskId);
 * param_4 := obj.ExecTop();
 */