class Solution {
public:
    inline bool isPossible(long long int time, int cars, vector<int>& ranks) {
        for (int& it : ranks) {
            long long int sq_val = time / (long long int)it;
            long long int n = sqrt(sq_val);
            
            cars -= n;
            if (cars <= 0)
                return true;
        }

        return cars <= 0;
    }

    long long repairCars(vector<int>& ranks, int cars) {
        long long int start = 0, end = cars;
        end *= cars;
        end *= *max_element(ranks.begin(), ranks.end());

        long long int mid, ans = end;
        while (start <= end) {
            mid = (end - start) / 2 + start;
            if (isPossible(mid, cars, ranks)) {
                ans = mid;
                end = mid - 1;
            } else
                start = mid + 1;
        }

        return ans;
    }
};