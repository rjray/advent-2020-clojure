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

Day 14 (3241/3076, approx. 1 hour 14 minutes). First part was pretty straightforward, it shouldn't have taken me the 35 minutes it did. I vapor-locked on how to best turn a decimal number into a vector of 36 (padded) binary digits. I'm not even sure that the way I did it is very clean. But I got the star and moved on.

In part 2, I made two rookie mistakes, one AoC mistake and one Clojure. The AoC rookie mistake was that I didn't read the description of part 2 closely enough, and my modified masking fn was incorrect from the outset. But the Clojure error actually bothers me more: I had nested `reduce` forms, and the inner one was returning it's computed result rather than adding it to the running list with `cons` like it should have. That was just silly of me. And I'm pretty sure there are better ways to do this part than what I've written, but it got the answer right on the first submit so I'll take it.

**Note**: For this day, the example input data was different between parts 1 and 2, so the "test" file for this day was never updated to be used for part 2. And the example data that got committed was for part 2, so the test file for this day won't work for either part...

## [day14bis.clj](day14bis.clj)

I didn't like the fact that the code was over 100 lines long, so I tried to shorten it. I trimmed 13 lines (down to 94) and got a speed-up of about 20% in the run-time of part 2. I do like parts of the second solution a little better as a result of this. I'm using strings for the addresses rather than vectors, and the way I fill in the X's in a post-masked address is cleaner and faster. There are probably more ways to shorten it still.

## [day15.clj](day15.clj)

Day 15 (4291/3393, approx. 49 minutes). Both parts of this are solved with a brute-force algorithm. And I don't know if I'll bother going back to refine it once I've read some other solutions on reddit. But I'm rather disappointed in myself today, because I got my second wrong submission for a really stupid reason: I hard-coded a number in the algorithm that worked on all the test data-sets but not on the real data. You never *never* **NEVER** hard-code a part of your algorithm.

To make it even more frustrating, when I tried running part 2 within a CIDER-driven REPL, it locked up my machine and nearly crashed it. I was able to kill it when it started to thrash, as that slowed it a bit. When I ran it again in an ordinary run (outside of a REPL and/or CIDER), it produced the (correct) answer within forty seconds or so.

This was not my day.

## [day16.clj](day16.clj)

Day 16 (3212/3883, approx. 1 hour 49 minutes). Parsing can be a pain, especially when the input has distinctly different sections. After getting a working parse, part 1 was pretty straightforward to do using sets. I finished it in under half an hour (most of which was the parser).

Part 2 was a challenge. It's some sort of search algorithm, and I don't know if I did the "right" thing or if what I did counts as brute force. But I got the right answer, so I'll take it.

The code is sitting at 116 lines, so I'll surely try to improve it later. Also, the unit-test file is worthless since the data had to change between parts 1 and 2, and the test-data for part 2 couldn't actually generate an answer.

## [day17.clj](day17.clj)

Day 17 (2433/2409, approx. 1 hour 7 minutes). We got to do Conway's Game of Life again. Only, in three dimensions! I really *must* get better at building complex data structures in Clojure. The majority of my part 1 time was in getting the 3-dimensional space correctly initialized. After that, I was able to crib a lot of code from the way I had done GoL on day 11. Mind you, this isn't fast. It's slow as hell. But it got the right answer on the first submission.

Part 2... did I say *three* dimensions? How about **four** dimensions? Let's play GoL with hypercubes! This was actually one of the easiest part-2's for me yet. Admittedly, in the name of haste I just copied the relevant fn's from part 1 and modified them for 4D. Thus, the code is 125 lines long. And if I thought the 3D version was slow...! But I also got a first-shot correct answer and I'm happy to take it.

Sitting here typing this up, I think I have an idea on how to do this without looping over the entire X × Y × Z (× W) space. I'll try that tomorrow, after some sleep...

## [day17bis.clj](day17bis.clj)

I sat down to try out what I thought was surely a novel approach to this. Out of curiousity, though, I looked at a few of the other Clojure solutions posted to the Slack. Turns out my approach isn't that novel. In fact, my approach would have been less efficient than the first one I looked at. The first one I looked at was the solution from Aleksandr Zhuravlёv ([here](https://github.com/zelark/AoC-2020/blob/master/src/zelark/aoc_2020/day_17.clj)), and it is quite elegant. Where I was planning to use an approach similar to an A* search, zelark's take on it was much more compact. In fact, the only thing here that I've added to his original is that I use a generator to make the "neighbors" function for an arbitrary number of dimensions. So there is just the one generator, used twice with 3 or 4 dimensions.

I particularly like his `step` function, and the approach to determining the next generation of cells. Where I would have had a "frontier" of cells to examine that would have included all the same cells he examines, I had planned to treat each one to an independant evaluation of counting neighbors. Instead, he uses the `frequencies` primitive on a list of the candidate cells *with duplicates* in order to determine for each cell how many living neighbors it currently has.

Both my search-based approach and this one have the same drawback, however: it would be very difficult to detect oscillating patterns in the field as a part of detecting cycles. Fortunately, for the sake of the Advent challenge we were only tasked with running the simulation a fixed number of steps and then counting the living cells. Which this approach does in under 1 second for the 4-dimensional case.

## [day18.clj](day18.clj)

Day 18 (4555/3364, approx. 1 hour 44 minutes). I sucked at this one, totally bombed it and had to look at other solutions to get it right. The only bright-side is that thanks to Vincent Cantin (again) I now have some exposure to `clojure.edn` and `clojure.walk`. That's all I want to say for now.

## [day19.clj](day19.clj)

Day 19 (1662/793, approx. 1 hour 8 minutes). Holy crap, I broke the top-1000 for the second half of this. This is because the Clojure library I used ([Instaparse](https://github.com/Engelberg/instaparse)) was not deterred by the caveats of part 2.

My approach was basically to transform the rules as given in the input into a grammar that Instaparse could understand. From that, most of the rest just fell into place. Might clean this up a little and write more tomorrow.

## [day19bis.clj](day19bis.clj)

This feels almost like cheating at optimization. Because the only real change I've made is that the Instaparse library did not in fact need me to massage the grammar rules. It was able to parse them at face-value, needing only `:start` and `:0` keywords to tell the parser that the starting rule's label is `0`. I also changed how the grammar is updated for part 2 to something more succinct and efficient that someone on the Clojure Slack suggested (my first exposure to the `reduce-kv` primitive).

This doesn't speed up the run-time at all, really. But it makes the code more concise.

## [day20.clj](day20.clj)

Day 20 (2392/8653). For this one, I took a shortcut from a clue that yielded a very quick answer for part 1. And code that was almost completely useless for part 2.

For part 1, I converted the sides of the tiles to integer numbers and used those in sets to determine how many of the edges appeared only once. The four tiles that would have two singles out of their four sides were the corners. Order didn't matter, so no problem.

But for part 2, it was necessary to actually assemble the map correctly, and search for "monsters" in one of the eight possible orientations of the final map. This is where I basically ate shit. Holding on to the numerical representation of sides, I ended up struggling with several aspects of the solution. When I was finally able to assemble the tiles in the proper order, I was unable to get the orientation of each individual tile correct. Why? Because I had misinterpreted the way to represent each orientation in terms of the numerical values of the sides. I finally wrote some additional code that took the known eight side-values (each of four sides forwards and backwards) and each of the eight orientations, and spit out the 4-tuple that I needed for each orientation. After that, I was able to get the correct answer for the test data. However, I botched the first submission of the part 2 answer because of a stringification bug that hadn't manifested in the test data.

I might be able to improve this code, but I have no desire to look at it again any time soon.

## [day21.clj](day21.clj)

Day 21 (1796/2155, approx. 1 hour 9 minutes). This was a breeze compared to day 20. Part 1 was a lot of set operations, and part 2 was similar to a search problems from an earlier day. Tired and burned out, so that's all for now.

## [day22.clj](day22.clj)

Day 22 (2545/1470, approx. 54 minutes). Now, *this* was a set of puzzles for which Clojure is eminently ideal. The ability to mix tail-recursion with standard recursion, all while keep your data structures safely intact, meant that I finished both in under an hour.

Part 1 was very simple. Took me 19 minutes, most of which was double-checking basic Clojure keywords (I have **GOT** to get better at this language). Using a `loop` form and `recur` was all that was needed. There's no real trick to it, he saved that for part 2.

For part 2, the card game became recursive. This was a little bit of a struggle at first, hence the 35 minutes needed. The tricky part was integrating a standard recursive call into the tail-recursive loop-flow. Clojure's sets took care of the infinite-loop detection. Literally the only debugging on part 2 was due to trying to re-use the scoring function from part 1. Which I did in the end, but it needed a wrapper in order to work.

## [day23.clj](day23.clj)

Day 23 (--/--).

## [day24.clj](day24.clj)

Day 24 (--/--).

## [day25.clj](day25.clj)

Day 25 (--/--).
