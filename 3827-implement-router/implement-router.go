type Packet struct {
	s, d, t int
}

type Router struct {
	limit int
	q     []Packet
	m     map[int][]int
}

func Constructor(memoryLimit int) Router {
	return Router{
		limit: memoryLimit,
		q:     make([]Packet, 0),
		m:     map[int][]int{},
	}
}

func (this *Router) AddPacket(source int, destination int, timestamp int) bool {
	p := Packet{source, destination, timestamp}
	for i := len(this.q) - 1; i >= 0; i-- {
		q := this.q[i]
		if q.t < p.t {
			break
		}
		if q == p {
			return false
		}
	}
	this.q = append(this.q, p)
	this.m[destination] = append(this.m[destination], timestamp)
	if len(this.q) > this.limit {
		this.m[this.q[0].d] = this.m[this.q[0].d][1:]
		this.q = this.q[1:]
	}
	return true
}

func (this *Router) ForwardPacket() []int {
	if len(this.q) == 0 {
		return []int{}
	}
	p := this.q[0]
	this.q = this.q[1:]
	this.m[p.d] = this.m[p.d][1:]
	return []int{p.s, p.d, p.t}
}

func (this *Router) GetCount(destination int, startTime int, endTime int) int {
	m := this.m[destination]
	return sort.Search(len(m), func(i int) bool {
		return m[i] > endTime
	}) - sort.Search(len(m), func(i int) bool {
		return m[i] >= startTime
	})
}

/**
 * Your Router object will be instantiated and called as such:
 * obj := Constructor(memoryLimit);
 * param_1 := obj.AddPacket(source,destination,timestamp);
 * param_2 := obj.ForwardPacket();
 * param_3 := obj.GetCount(destination,startTime,endTime);
 */

/**
 * Your Router object will be instantiated and called as such:
 * obj := Constructor(memoryLimit);
 * param_1 := obj.AddPacket(source,destination,timestamp);
 * param_2 := obj.ForwardPacket();
 * param_3 := obj.GetCount(destination,startTime,endTime);
 */