class Solution {
public:
    long long repairCars(vector<int>& ranks, int cars) {
        sort(ranks.begin(), ranks.end());

        ll left = 1;
        ll right = (ll)ranks[0] * cars * cars;
        ll answer = right;

        while (left <= right) {
            ll mid = left + (right - left) / 2;

            if (canRepair(ranks, cars, mid)) {
                answer = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return answer;
    }

    typedef long long ll;

    bool canRepair(const vector<int>& ranks, int cars, ll time) {
        ll totalCars = 0;

        for (int r : ranks) {
            ll carsPerMechanic = sqrt(time / r);
            totalCars += carsPerMechanic;

            if (totalCars >= cars)
                return true;
        }

        return totalCars >= cars;
    }
};