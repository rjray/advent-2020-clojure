# Breakdown of Files

Jump to day: [1](#day01clj)&nbsp;|&nbsp;[2](#day02clj)&nbsp;|&nbsp;[3](#day03clj)&nbsp;|&nbsp;[4](#day04clj)&nbsp;|&nbsp;[5](#day05clj)&nbsp;|&nbsp;[6](#day06clj)&nbsp;|&nbsp;[7](#day07clj)&nbsp;|&nbsp;[8](#day08clj)&nbsp;|&nbsp;[9](#day09clj)&nbsp;|&nbsp;[10](#day10clj)&nbsp;|&nbsp;[11](#day11clj)&nbsp;|&nbsp;[12](#day12clj)&nbsp;|&nbsp;[13](#day13clj)&nbsp;|&nbsp;[14](#day14clj)&nbsp;|&nbsp;[15](#day15clj)&nbsp;|&nbsp;[16](#day16clj)&nbsp;|&nbsp;[17](#day17clj)&nbsp;|&nbsp;[18](#day18clj)&nbsp;|&nbsp;[19](#day19clj)&nbsp;|&nbsp;[20](#day20clj)&nbsp;|&nbsp;[21](#day21clj)&nbsp;|&nbsp;[22](#day22clj)&nbsp;|&nbsp;[23](#day23clj)&nbsp;|&nbsp;[24](#day24clj)&nbsp;|&nbsp;[25](#day25clj)

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

## [day10.clj](day10.clj)

Day 10 (4908/7163, approx. 2 hours 37 minutes). Part 1 is deceptively easy. As in, once I realized what it was it took about 2 minutes to code. But it took too long to get to that point.

But part 2...? I spent over two hours on it. I finally got something that worked, but along the way I had two different iterations of a solution that ran for over 20 minutes each without returning a value. I was furiously reading up on memoization and trying to apply that here. I finally got something that works, but I will admit that I helped myself along by looking at a few other completed solutions. I feel like I learned a *LOT* from part 2, but it's painful to have spent this much time so early in the challenge...

**Edit**: I want to credit [Vincent Cantin](https://github.com/green-coder) for the solution that ended up influencing my part 2 solution the most. I was already doing something very similar to his code, but I had placed `memoize` in the wrong place. Understanding the differences between his solution and my initial attempts was very educational.

## [day10bis.clj](day10bis.clj)

I didn't change much here. In part 1, I replaced the use of `(group-by identity ...)` with `(frequencies ...)`. This is maybe faster, but definitely more concise. In part 2, I mostly moved a let-form up one level since it didn't need to be re-evaluated in the recursive calls.

## [day10bench.clj](day10bench.clj)

This is a special file, not tied in to the core (so it can't be run from `lein`). Earlier today I was reading the [day 10 solutions mega-thread](https://old.reddit.com/r/adventofcode/comments/ka8z8x/2020_day_10_solutions/) on reddit and came across a comment thread in which someone demonstrated two different dynamic programming approaches to part 2. Both promised O(n) complexity. So I was really curious about this and implemented both of them in Clojure. This file consists of those two DP solutions along with my solution (the one influenced by Vincent Cantin). It reads the day 10 data and pre-processes it into a vector, and uses this vector to test and benchmark each of the three. The benchmarking is done with the [Criterium](https://github.com/hugoduncan/criterium) package for Clojure.

Much to my surprise, the original (non-DP, recursive) solution outperformed both of the DP implementations. I'd love to hear from any more-experienced Clojure devs who look at this and can see any improvements.

## [day11.clj](day11.clj)

Day 11 (4241/4454, approx. 1 hour 29 minutes). For today, the theme was [Conway's Game of Life](https://en.wikipedia.org/wiki/Conway%27s_Game_of_Life). Part 1 was basically that with an added constraint of some cells being kept empty permanently. After spending too long on parsing the input map (which defined which cells were or were not in use), I was able to solve it fairly easily. Funny-enough, there was a Life-esque day last year, but (not surprisingly) this was so different that I couldn't really reuse any of that code.

Part 2 changed the rule in subtle-but-significant ways. Instead of counting *adjacent* cells, you counted *visible* in-use cells in the eight directions. As in, if the upper-left cell was not in use then you go to the next one on that slope/direction until you find one that is in-use. Then you count it as either empty or not for the overall count. Funny-enough, part 2 took less time to complete than part 1 despite a subtle bug that took a while to find and resolve.

This was a puzzle in which Clojure's immutable data really shines. Being able to compute a new "floor" for each iteration without having to worry about the current one getting corrupted was invaluable. I'm not happy with the 105-line length of the code, though. In the name of haste I duplicated a lot of code, since it was faster to copy a fn and change one or two bits than to re-write the original to be flexible. There will definitely be a (shorter) `day11bis.clj` later on.

## [day11bis.clj](day11bis.clj)

I wasn't able to get much performance improvement out of this, but I was able to trim 26 lines by removing redundant code. I also replaced a couple of `cond` forms with `case`, which did get a small speed-up. I imagine there is a way to compute this (a dynamic programming approach, maybe) that doesn't involve actually iterating the field. I may look into that later, but for now I'll take these improvements.

## [day12.clj](day12.clj)

Day 12 (4023/3351, approx. 52 minutes). This one had some interesting elements to it. In part 1, you just "move" a ferry boat around based on a stream of instructions. You have to keep track of which direction it is currently facing for the sake of "forward" movement and turns. Then calculate the Manhattan distance of the final point. Took about 30 of the 52 minutes, as I let myself get caught up in how to best "turn" the boat some multiple of 90 degrees in either a left or right direction.

Part 2 threw a slight curve-ball at it. Now, the position that moves is a waypoint, and only one of the commands actually moves the boat based on the current position of the waypoint relative to the boat. This one took a little less time because the only tricky part was implementing rotation of the waypoint for the turn commands. For part 2 I got my best finishing position since day 1.

At this point, I'm starting to get a clearer idea of what I should have already ready to go for things in the future. I find I'm losing most of my up-front time in trying to get a good parse of the input data. I think that having a library of some of the more typical formats pre-canned would help me.

Though only 71 lines, I feel like the solution can be shorter and cleaner. I'll do a `day12bis.clj` later, after I've gone back and tuned day 11's code.

## [day12bis.clj](day12bis.clj)

To my surprise, I wasn't really able to shorten this very much. I replaced `cond` with `case` in a few places, which is a tiny bit faster, but I'm not sure that there's much difference overall. Looking back at it, I'm already pretty happy with my routines for doing turns (both part 1 and part 2). Not much else was that drastically different between the two parts.

## [day13.clj](day13.clj)

Day 13 (5070/2144, approx. 1 hour 21 minutes). Well. Today was interesting. Firstly, it didn't take me *that* long to finish part 1, it was just pretty simple so lots of people got in ahead of me.

But part 2 earned it's extra time. The theme of the day was, apparently, the [Chinese Remainder Theorem](https://en.wikipedia.org/wiki/Chinese_remainder_theorem). I'd never heard of this, so it wasn't until I caved and went to reddit that I saw everyone mentioning it. So I left reddit and looked it up, only to find nothing but heavy mathematical notation. I went looking for a simple implementation that I could translate to Clojure, and finally found a pretty succint one in Python. But along the path to this, I wasted time on LCM and other things that turned out to have nothing to do with the solution.

## [day14.clj](day14.clj)

Day 14 (--/--).

## [day15.clj](day15.clj)

Day 15 (--/--).

## [day16.clj](day16.clj)

Day 16 (--/--).

## [day17.clj](day17.clj)

Day 17 (--/--).

## [day18.clj](day18.clj)

Day 18 (--/--).

## [day19.clj](day19.clj)

Day 19 (--/--).

## [day20.clj](day20.clj)

Day 20 (--/--).

## [day21.clj](day21.clj)

Day 21 (--/--).

## [day22.clj](day22.clj)

Day 22 (--/--).

## [day23.clj](day23.clj)

Day 23 (--/--).

## [day24.clj](day24.clj)

Day 24 (--/--).

## [day25.clj](day25.clj)

Day 25 (--/--).
