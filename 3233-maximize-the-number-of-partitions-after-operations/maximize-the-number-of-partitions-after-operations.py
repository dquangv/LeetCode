class Solution:
    def maxPartitionsAfterOperations(self, s: str, k: int) -> int:
        n = len(s)
        set_bits = [1 << (ord(letter) - ord("a")) for letter in s]

        def make_prefix(set_bits: List[int]):
            prefix = [0]
            prefix_mask = [0]
            mask = 0
            groups = 0
            for current_index_set_bits in set_bits:
                mask |= current_index_set_bits
                if mask.bit_count() > k:
                    groups += 1
                    mask = current_index_set_bits
                prefix.append(groups)
                prefix_mask.append(mask)
            return prefix, prefix_mask

        prefix, prefix_mask = make_prefix(set_bits)
        suffix, suffix_mask = make_prefix(set_bits[::-1])

        max_partitions_after_operations = 0
        for index in range(n):
            candidate = prefix[index] + suffix[-(index + 2)]
            mask = prefix_mask[index] | suffix_mask[-(index + 2)]
            if min(mask.bit_count() + 1, 26) <= k:
                candidate += 1
            elif (
                prefix_mask[index].bit_count()
                == suffix_mask[-(index + 2)].bit_count()
                == k
                and mask.bit_count() < 26
            ):
                candidate += 3
            else:
                candidate += 2
            max_partitions_after_operations = max(
                max_partitions_after_operations, candidate
            )

        return max_partitions_after_operations
