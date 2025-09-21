type MovieRentingSystem struct {
	available   map[[2]int]int     // (shop, movie) -> price
	movieShops  map[int][][2]int   // movie -> slice of (price, shop)
	rented      map[[2]int]bool    // rented set
}

// Constructor
func Constructor(n int, entries [][]int) MovieRentingSystem {
	mrs := MovieRentingSystem{
		available:  make(map[[2]int]int),
		movieShops: make(map[int][][2]int),
		rented:     make(map[[2]int]bool),
	}

	for _, e := range entries {
		shop, movie, price := e[0], e[1], e[2]
		key := [2]int{shop, movie}
		mrs.available[key] = price
		mrs.movieShops[movie] = append(mrs.movieShops[movie], [2]int{price, shop})
	}

	// Sort each movie’s shops by (price, shop)
	for movie := range mrs.movieShops {
		sort.Slice(mrs.movieShops[movie], func(i, j int) bool {
			if mrs.movieShops[movie][i][0] == mrs.movieShops[movie][j][0] {
				return mrs.movieShops[movie][i][1] < mrs.movieShops[movie][j][1]
			}
			return mrs.movieShops[movie][i][0] < mrs.movieShops[movie][j][0]
		})
	}

	return mrs
}

// Search: return up to 5 shops where the movie is available (not rented), sorted by price then shop
func (mrs *MovieRentingSystem) Search(movie int) []int {
	result := []int{}
	if shops, ok := mrs.movieShops[movie]; ok {
		for _, ps := range shops {
			price, shop := ps[0], ps[1]
			_ = price
			key := [2]int{shop, movie}
			if !mrs.rented[key] {
				result = append(result, shop)
				if len(result) == 5 {
					break
				}
			}
		}
	}
	return result
}

// Rent: mark a movie as rented
func (mrs *MovieRentingSystem) Rent(shop, movie int) {
	key := [2]int{shop, movie}
	mrs.rented[key] = true
}

// Drop: mark a movie as available again
func (mrs *MovieRentingSystem) Drop(shop, movie int) {
	key := [2]int{shop, movie}
	delete(mrs.rented, key)
}

// Report: return up to 5 rented movies sorted by (price, shop, movie)
func (mrs *MovieRentingSystem) Report() [][]int {
	rentedList := [][3]int{}
	for key := range mrs.rented {
		price := mrs.available[key]
		shop, movie := key[0], key[1]
		rentedList = append(rentedList, [3]int{price, shop, movie})
	}

	sort.Slice(rentedList, func(i, j int) bool {
		if rentedList[i][0] != rentedList[j][0] {
			return rentedList[i][0] < rentedList[j][0]
		}
		if rentedList[i][1] != rentedList[j][1] {
			return rentedList[i][1] < rentedList[j][1]
		}
		return rentedList[i][2] < rentedList[j][2]
	})

	result := [][]int{}
	for i := 0; i < len(rentedList) && i < 5; i++ {
		result = append(result, []int{rentedList[i][1], rentedList[i][2]})
	}
	return result
}

/**
 * Your MovieRentingSystem object will be instantiated and called as such:
 * obj := Constructor(n, entries);
 * param_1 := obj.Search(movie);
 * obj.Rent(shop,movie);
 * obj.Drop(shop,movie);
 * param_4 := obj.Report();
 */