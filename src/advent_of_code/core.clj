(ns advent-of-code.core
  (:require [advent-of-code.day01]
            [advent-of-code.day02]
            [advent-of-code.day03]
            [advent-of-code.day04]
            [advent-of-code.day05]
            [advent-of-code.day06]
            [advent-of-code.day07]
            [advent-of-code.day08]
            [advent-of-code.day09]
            [advent-of-code.day10]
            [advent-of-code.day11]
            [advent-of-code.day12]
            [advent-of-code.day13]
            [advent-of-code.day14]
            [advent-of-code.day15]
            [advent-of-code.day16]
            [advent-of-code.day17]
            [advent-of-code.day18]
            [advent-of-code.day19]
            [advent-of-code.day20]
            [advent-of-code.day21]
            [advent-of-code.day22]
            [advent-of-code.day23]
            [advent-of-code.day24]
            [advent-of-code.day25]))

(defn read-input
  [day]
  (slurp (clojure.java.io/resource day)))

(defn -main
  "Used to dispatch tasks from the command line.
  
  lein run d01.p1"
  [part]
  (case part
    "d01.p1" (println (advent-of-code.day01/part-1 (read-input "day01.txt")))
    "d01.p2" (println (advent-of-code.day01/part-2 (read-input "day01.txt")))
    "d02.p1" (println (advent-of-code.day02/part-1 (read-input "day02.txt")))
    "d02.p2" (println (advent-of-code.day02/part-2 (read-input "day02.txt")))
    "d03.p1" (println (advent-of-code.day03/part-1 (read-input "day03.txt")))
    "d03.p2" (println (advent-of-code.day03/part-2 (read-input "day03.txt")))
    "d04.p1" (println (advent-of-code.day04/part-1 (read-input "day04.txt")))
    "d04.p2" (println (advent-of-code.day04/part-2 (read-input "day04.txt")))
    "d05.p1" (println (advent-of-code.day05/part-1 (read-input "day05.txt")))
    "d05.p2" (println (advent-of-code.day05/part-2 (read-input "day05.txt")))
    "d06.p1" (println (advent-of-code.day06/part-1 (read-input "day06.txt")))
    "d06.p2" (println (advent-of-code.day06/part-2 (read-input "day06.txt")))
    "d07.p1" (println (advent-of-code.day07/part-1 (read-input "day07.txt")))
    "d07.p2" (println (advent-of-code.day07/part-2 (read-input "day07.txt")))
    "d08.p1" (println (advent-of-code.day08/part-1 (read-input "day08.txt")))
    "d08.p2" (println (advent-of-code.day08/part-2 (read-input "day08.txt")))
    "d09.p1" (println (advent-of-code.day09/part-1 (read-input "day09.txt")))
    "d09.p2" (println (advent-of-code.day09/part-2 (read-input "day09.txt")))
    "d10.p1" (println (advent-of-code.day10/part-1 (read-input "day10.txt")))
    "d10.p2" (println (advent-of-code.day10/part-2 (read-input "day10.txt")))
    "d11.p1" (println (advent-of-code.day11/part-1 (read-input "day11.txt")))
    "d11.p2" (println (advent-of-code.day11/part-2 (read-input "day11.txt")))
    "d12.p1" (println (advent-of-code.day12/part-1 (read-input "day12.txt")))
    "d12.p2" (println (advent-of-code.day12/part-2 (read-input "day12.txt")))
    "d13.p1" (println (advent-of-code.day13/part-1 (read-input "day13.txt")))
    "d13.p2" (println (advent-of-code.day13/part-2 (read-input "day13.txt")))
    "d14.p1" (println (advent-of-code.day14/part-1 (read-input "day14.txt")))
    "d14.p2" (println (advent-of-code.day14/part-2 (read-input "day14.txt")))
    "d15.p1" (println (advent-of-code.day15/part-1 (read-input "day15.txt")))
    "d15.p2" (println (advent-of-code.day15/part-2 (read-input "day15.txt")))
    "d16.p1" (println (advent-of-code.day16/part-1 (read-input "day16.txt")))
    "d16.p2" (println (advent-of-code.day16/part-2 (read-input "day16.txt")))
    "d17.p1" (println (advent-of-code.day17/part-1 (read-input "day17.txt")))
    "d17.p2" (println (advent-of-code.day17/part-2 (read-input "day17.txt")))
    "d18.p1" (println (advent-of-code.day18/part-1 (read-input "day18.txt")))
    "d18.p2" (println (advent-of-code.day18/part-2 (read-input "day18.txt")))
    "d19.p1" (println (advent-of-code.day19/part-1 (read-input "day19.txt")))
    "d19.p2" (println (advent-of-code.day19/part-2 (read-input "day19.txt")))
    "d20.p1" (println (advent-of-code.day20/part-1 (read-input "day20.txt")))
    "d20.p2" (println (advent-of-code.day20/part-2 (read-input "day20.txt")))
    "d21.p1" (println (advent-of-code.day21/part-1 (read-input "day21.txt")))
    "d21.p2" (println (advent-of-code.day21/part-2 (read-input "day21.txt")))
    "d22.p1" (println (advent-of-code.day22/part-1 (read-input "day22.txt")))
    "d22.p2" (println (advent-of-code.day22/part-2 (read-input "day22.txt")))
    "d23.p1" (println (advent-of-code.day23/part-1 (read-input "day23.txt")))
    "d23.p2" (println (advent-of-code.day23/part-2 (read-input "day23.txt")))
    "d24.p1" (println (advent-of-code.day24/part-1 (read-input "day24.txt")))
    "d24.p2" (println (advent-of-code.day24/part-2 (read-input "day24.txt")))
    "d25.p1" (println (advent-of-code.day25/part-1 (read-input "day25.txt")))
    "d25.p2" (println (advent-of-code.day25/part-2 (read-input "day25.txt")))
    (println "not found")))

