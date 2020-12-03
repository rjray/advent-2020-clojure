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

(Note that since this file is out of the usual `dayNN.clj` rotation, it doesn't run from the `core.clj` harness that those do. So I've copied the `read-input` fn here to keep it simpler.)

## [day03.clj](day03.clj)

Day 3 (4520/4482, approx 29 minutes). This one was much more fun than day 2 had been. I spent too much time on parsing the input into a usable data structure than I should have. Once I had part 1 done, I quickly learned that I should have parameterized the two slope values for the calculations. Which I KNEW I should have when I was writing it, but when you're in a hurry...

Part 2 was pretty quick after part 1 was done, I was slowed by an overflow error stemming from forgetting that one of the new slopes could move past the end of the Y-range, which hadn't been an issue in part 1. Still, I improved my performance over day 2 and jumped up a few places on the two leaderboards I'm on (NVIDIA and Clojurians).

## day04.clj

Day 4 (--/--).

## day05.clj

Day 5 (--/--).

## day06.clj

Day 6 (--/--).

## day07.clj

Day 7 (--/--).

## day08.clj

Day 8 (--/--).

## day09.clj

Day 9 (--/--).

## day10.clj

Day 10 (--/--).

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
