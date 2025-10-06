class Solution:
    def swimInWater(self, grid: List[List[int]]) -> int:
            q=[]
            n=len(grid)
            heapq.heappush(q,(grid[0][0],0,0))
            vis=set()
            vis.add((0,0))
            
            while q:
                t,x,y=heapq.heappop(q)
                if x==n-1 and y==n-1:
                    return t
                for dx,dy in [(0,-1),(0,1),(1,0),(-1,0)]:
                    X=x+dx
                    Y=y+dy
                    if 0<=X<n and 0<=Y<n and (X,Y) not in vis:
                        vis.add((X,Y))
                        heapq.heappush(q,(max(t,grid[X][Y]),X,Y))

            return  -1