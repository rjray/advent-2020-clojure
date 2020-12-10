# Breakdown of Files

Here is a breakdown of the various files in this directory. Files with names of the form `dayNN.clj` represent the code actually used to solve the problems (with some tweaking done using a static analysis plug-in for Leinengen). Files with `bis` in the name are modified/tuned versions of the given original day. (If you see comments in a file, I can promise you they were added after the fact.)

The numbers in parentheses in the descriptions of the files represent the rank I had for when my solutions were submitted and accepted.

## [day01.clj](day01.clj)

Day 1 (2863/2417, approx. 16 minutes). This was a combinatorics problem, for which I let `clojure.math.combinatorics` do the heavy lifting of combinations. Paired it with `filter` to find the one pair that totalled 2020 and multiplied them. The second part was to find *three* numbers that totalled 2020, which just meant changing `2` to `3` in the combinatorics call.

**Addendum**: According to [this reddit thread](https://www.reddit.com/r/adventofcode/comments/k4ejjz/2020_day_1_unlock_crash_postmortem/), the sudden influx of traffic overloaded the instances they had configured on AWS. As such, they'll be canceling the global leaderboard points for both halves of day 1. I'm leaving my numbers up here just for reference.

## [day02.clj](day02.clj)

Day 2 (4900/5635, approx. 41 minutes). This wasn't NEARLY as hard as my rankings suggest. A couple of dumb typos caused me to waste nearly 15 minutes in debugging for the first part. Then my brain vapor-locked on how to do XOR and cost me time in part 2. Not happy with this day's performance.

## [day02bis.clj](day02bis.clj)

I was so annoyed with myself over my poor performance on this day's puzzles that I almost immediately went looking for improvement. The main improvement here is using a single regexp to get all the relevant parts of the text line. Really, after 25+ years of Perl I should have done that coming out of the gate.

## [day03.clj](day03.clj)

Day 3 (4520/4482, approx. 29 minutes). This one was much more fun than day 2 had been. I spent too much time on parsing the input into a usable data structure than I should have. Once I had part 1 done, I quickly learned that I should have parameterized the two slope values for the calculations. Which I KNEW I should have when I was writing it, but when you're in a hurry...

Part 2 was pretty quick after part 1 was done. I was slowed by an overflow error stemming from forgetting that one of the new slopes could move past the end of the Y-range, which hadn't been an issue in part 1. Still, I improved my performance over day 2 and jumped up a few places on the two leaderboards I'm on (NVIDIA and Clojurians).

## [day03bis.clj](day03bis.clj)

I wasn't annoyed with my day 3 code (as was the case with day 2), but I knew it could be cleaner. The basic algorithm is sound, the code just lacked any real elegance.

## [day04.clj](day04.clj)

Day 4 (5558/4783, approx. 1 hour 6 minutes). Wow, this was harder than it needed to be (for me, that is). The first part wasn't too bad, but I fumbled on building the sort of data-structure I needed for it. But the second half was just harsh... I made too many bad assumptions and paid the price. Invalid data comes in many, many forms...

## [day04bis.clj](day04bis.clj)

After sleeping on it, I'm more annoyed with the day 4 code than I was in the moment. It stands at 71 lines, and that had to be something I can fix. I got it down to 58 lines and noticably cleaner. I'm definitely happier with the conversion of the input text to passport structures.

(Also, at this point I decided to just add the `bis` files to the `advent-of-code.core` run-harness to make it easier to run the tests.)

## [day05.clj](day05.clj)

Day 5 (5181/5400, approx. 42 minutes). Overall better than yesterday, though I struggled with the wording of part 2, and that delayed me in getting it done. Once I understood it, it was actually quite easy (but I'd already paid a time-penalty). Binary partitioning is the theme of the day, and while I'll commit the code as-is, I'll be returning to it to make it shorter. I replicated a lot of code for the sake of expediency.

Side note: for the second part of the problem, the example input was of no use for testing the solution before I tried running it on the actual input data. That made submitting my part 2 answer a little nerve-wracking...

## [day05bis.clj](day05bis.clj)

In fact, this is the first time I didn't even want to wait to make the improved version. This reduces the dual partition fns to a single one, and (more importantly) gets rid of a lot of redundant/unnecessary steps.

## [day06.clj](day06.clj)

Day 6 (3664/3373, approx. 18 minutes).

Boy, was it nice to have one where I didn't vapor-lock at any point. If I could type faster I'd have gotten a better position. The key to this one (both parts) stems from Clojure supporting sets as first-class datatypes. I'm not even sure I can clean this one up, so there might not be a `day06bis.clj`.

## [day07.clj](day07.clj)

Day 7 (5162/4001, approx. 1 hour 11 minutes). Nifty... our first real search problem. This was a sort of graph-search variant, I *think*. To be fair, I'm not quite sure. But I was able to solve both parts with minor recursive solutions. Spent about 15 minutes of the time getting a good parsing of the input data, and about 45 minutes trying to solve the first part. I had some misunderstanding about `if`-clauses and how they'd work with things like `filter`. Part 2 was actually considerably easier for me, taking only about 10-11 minutes of the total time.

## [day08.clj](day08.clj)

Day 8 (4956/4216, approx. 35 minutes). This one was outright fun. I got a slightly-late start due to being out looking at a Christmas lights display, so the approximate time is based on when I actually started. Part 1 was to emulate a simple three-instruction machine until it got into a loop. That was quite easy using a `set` instance to track the "lines" that had been visited. Part 2 was not really that much harder, but an off-by-one error slowed me down. For part 2, you had to change exactly one instruction in order to prevent the infinite loop. You had to change either a `jmp` to a `nop` or vice-versa. Here, the immutable nature of Clojure data-types serves you well, as you can make changes within a vector without altering the underlying vector. I used this to make *N* copies of the code with one change, then ran all of them until one comes back without getting into a loop.

Might clean this up some tomorrow, but I'm not really dissatisfied with the code as it is.

## [day08bis.clj](day08bis.clj)

Went ahead and did some low-level tweaking here, but didn't really see any speed-up. I do think that using a keyword lookup for the `filter` predicate looks cleaner than a string comparison did, at least. I also adjusted `parse-instructions` to return the result as a vector so that it isn't being converted to a vector in two other places, but I didn't expect that to save any real time. Likewise, I added `distinct` to the pipeline in part 2 (between `make-variants` and `map`), but it also had no noticeable effect on run-time.

## [day09.clj](day09.clj)

Day 9 (7279/6149, approx. 45 minutes). Ouch. Day 9, and I got my first incorrect submission. On the first part, no less. An off-by-one error bit me, and it didn't keep the test-data from producing the correct sample answer. Hence the worse-than-usual ranking for part 1. Once I found the problem it was fine, the second submission passed. Part 2 of the problem was actually much easier, despite the fact that I took a brute-force approach. I'm quite sure there is a faster, more-elegant solution to part 2. I may look at some other players' solutions and see.

## day10.clj

Day 10 (4908/7163, approx. 2 hours 37 minutes). Part 1 is deceptively easy. As in, once I realized what it was it took about 2 minutes to code. But it took too long to get to that point.

But part 2...? I spent over two hours on it. I finally got something that worked, but along the way I had two different iterations of a solution that ran for over 20 minutes each without returning a value. I was furiously reading up on memoization and trying to apply that here. I finally got something that works, but I will admit that I helped myself along by looking at a few other completed solutions. I feel like I learned a *LOT* from part 2, but it's painful to have spent this much time so early in the challenge...

## day11.clj

Day 11 (--/--).

## day12.clj

Day 12 (--/--).

## day13.clj

Day 13 (--/--).

## day14.clj

Day 14 (--/--).

## day15.clj

Day 15 (--/--).

## day16.clj

Day 16 (--/--).

## day17.clj

Day 17 (--/--).

## day18.clj

Day 18 (--/--).

## day19.clj

Day 19 (--/--).

## day20.clj

Day 20 (--/--).

## day21.clj

Day 21 (--/--).

## day22.clj

Day 22 (--/--).

## day23.clj

Day 23 (--/--).

## day24.clj

Day 24 (--/--).

## day25.clj

Day 25 (--/--).
