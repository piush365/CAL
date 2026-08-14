# Design and Analysis of Algorithm Lab — Week 1 Assignment (Part 1: Sorting)

Walchand College of Engineering, Sangli — CSE, Third Year
Batch: AT4

## Contents

| File | Question | Approach | Complexity |
|---|---|---|---|
| `src/Q1_MergeSortedArrays.java` | Merge sorted B into A (with buffer) | Fill from the back, in-place | O(n + m) time, O(1) space |
| `src/Q2_GroupAnagrams.java` | Sort strings so anagrams are adjacent | Group by sorted-character signature (HashMap) | O(n·k log k) time |
| `src/Q3_SearchRotatedArray.java` | Search a rotated sorted array | Modified binary search | O(log n) time |
| `src/Q4_SortHugeFile_Explanation.md` | Sort a 20GB file | External merge sort (chunk + K-way merge) | O(N log N) time, bounded memory |
| `src/Q5_SparseSearch.java` | Search a sorted array interspersed with `""` | Binary search that skips outward past empties | O(log n) avg, O(n) worst |
| `src/Q6_SearchSortedMatrix.java` | Search a row/column sorted matrix | Staircase search from top-right corner | O(M + N) time |
| `src/Q7_CircusTower.java` | Longest tower of strictly shorter & lighter people | Sort by height (ties by weight desc) + LIS on weight | O(n log n) time |
| `src/Q8_StreamRank.java` | Streaming rank-of-number tracker | Binary Indexed Tree (Fenwick Tree) | O(log MAX_VALUE) per op |

## How to compile & run

Each `.java` file is self-contained with its own `main()` method for a quick demo run.

```bash
cd src
javac -encoding UTF-8 Q1_MergeSortedArrays.java
java Q1_MergeSortedArrays
```

Repeat for any other file (`Q2_GroupAnagrams`, `Q3_SearchRotatedArray`,
`Q5_SparseSearch`, `Q6_SearchSortedMatrix`, `Q7_CircusTower`, `Q8_StreamRank`).

All programs have been compiled and test-run; each one's output matches the
expected result given in the assignment, with two noted exceptions where the
assignment's own worked example appears to contain a minor inconsistency
(explained directly in code comments):

- **Q5**: for the array exactly as given in the assignment, `"ball"` is
  truly at index **3**, not 4 — the algorithm is verified correct against
  the array as written.
- **Q8**: `getRankOfNumber(x)` is implemented as *(count of tracked values
  ≤ x) − (1 if x has been tracked, else 0)*, which is the interpretation
  of "not including x itself" that reproduces the assignment's exact
  expected outputs (0, 1, 3) — see the comment in `Q8_StreamRank.java`
  for the full derivation.

## Note on Q4

Q4 asks for an *explanation*, not code — sorting a 20GB file is an
external-memory (disk-based) problem, not something you solve with a
simple in-memory Java routine. The write-up in
`Q4_SortHugeFile_Explanation.md` covers the external merge sort approach
(chunk, sort each chunk, then K-way merge with a min-heap) along with its
complexity analysis.
