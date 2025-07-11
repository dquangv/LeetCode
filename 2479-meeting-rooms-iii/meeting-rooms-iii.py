class Solution:
    def mostBooked(self, n: int, meetings: list[list[int]]) -> int:
        meetings.sort()  # Sort by meeting start time
        room_meet_count = [0] * n  # Count of meetings per room
        room_end_times = [0] * n  # When each room becomes available

        for start, end in meetings:
            assigned = False
            earliest_index = -1
            earliest_end_time = float("inf")

            for i in range(n):
                if room_end_times[i] < earliest_end_time:
                    earliest_end_time = room_end_times[i]
                    earliest_index = i

                if room_end_times[i] <= start:
                    room_end_times[i] = end
                    room_meet_count[i] += 1
                    assigned = True
                    break

            if not assigned:
                duration = end - start
                room_end_times[earliest_index] += duration
                room_meet_count[earliest_index] += 1

        # Find the room with most meetings
        max_meetings = max(room_meet_count)
        for i in range(n):
            if room_meet_count[i] == max_meetings:
                return i
