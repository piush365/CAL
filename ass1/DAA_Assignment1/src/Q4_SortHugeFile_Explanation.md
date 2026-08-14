# Q4) Sorting a 20GB file with one string per line

This problem does not need a code snippet — it needs an approach, because
a 20GB file almost certainly cannot fit entirely into RAM on a typical
machine. This is the classic **External Sorting** problem, solved with
**External Merge Sort**.

## Approach: External Merge Sort (K-way merge)

### Step 1 — Split into sorted chunks
- Read the huge file in chunks that DO fit comfortably in available
  memory (e.g., 500MB at a time, depending on how much RAM is free).
- Sort each chunk in memory using a standard efficient in-memory sort
  (e.g., Java's `Collections.sort()` / Arrays.sort(), which is a
  Dual-Pivot Quicksort / TimSort hybrid, O(n log n)).
- Write each sorted chunk back out to disk as a separate temporary file.
  A 20GB file split into 500MB chunks produces ~40 sorted temp files.

### Step 2 — K-way merge
- Now we have ~40 individually-sorted files. Open all of them
  simultaneously, but only read a small buffer from the front of each
  into memory at once (not the whole file).
- Use a **Min-Heap (Priority Queue)** of size K (K = number of chunk
  files). Each heap entry holds the current smallest unread value from
  one chunk file, plus a reference to which file it came from.
- Repeatedly:
    1. Pop the minimum element from the heap — write it to the final
       output file.
    2. Read the next value from the same source chunk file that the
       popped element came from, and push it onto the heap.
    3. Repeat until all chunk files are exhausted.
- This produces one fully sorted 20GB output file, written incrementally,
  never needing the entire dataset in memory.

## Why this works
- Memory usage is bounded by (chunk size during Step 1) and
  (K small read buffers + heap of size K during Step 2) — never the
  full 20GB.
- Disk I/O is sequential (reading/writing chunks front-to-back), which
  is efficient even on spinning disks, and especially fast on SSDs.

## Complexity
- Let N = total number of lines/records, and the file is split into
  K chunks.
- **Step 1 (sort chunks):** O(N log(N/K)) total, since each chunk of
  size N/K is sorted independently.
- **Step 2 (K-way merge):** O(N log K), since each of the N elements
  is pushed/popped from a heap of size K.
- **Overall:** O(N log N) time, O(chunk size) additional memory, plus
  O(N) total disk space for temp files.

## Practical notes
- If the file is still too large for a single machine's disk, this
  same idea generalizes to a **distributed external sort** (e.g., the
  approach used by MapReduce / Hadoop's shuffle-and-sort phase, or
  tools like the Unix `sort` command with `--parallel` and
  `--temporary-directory` flags, which already implement external
  merge sort internally).
- Unix's built-in `sort file.txt -o sorted.txt` command is, in
  practice, exactly this algorithm and can be used directly for very
  large files without writing custom code.
