(ns advent-of-code.day17bis
  (:require [clojure.string :as str]
            [clojure.math.combinatorics :as comb]))

;; The algorithm below is something I came up with independently, but is also
;; almost exactly like the algorithm used by Aleksandr Zhuravlёv in his file
;; https://github.com/zelark/AoC-2020/blob/master/src/zelark/aoc_2020/day_17.clj
;;
;; Instead of trying to make mine even more succinct, I'm going to try to
;; generalize it a little bit more. Most of this will still resemble zelark's
;; original.

;; I particularly like this way of turning the NxN input into a set of coords
;; that are padded as needed for the z (and then w) dimensions.
(defn parse-input [input n]
  (->> (str/split-lines input)
       (mapcat (fn [y line]
                 (keep-indexed (fn [x ch] (when (= ch \#) [x y])) line))
               (range))
       (map #(into % (repeat n 0)))
       set))

;; This is another interesting pattern: my approach had been to treat this
;; much like a search problem in which the starting frontier would have been
;; the set of "living" cells. I would have built up the frontier with the
;; neighbors as I pulled cells out of it. Instead, here zelark gathers all the
;; neighbors *with repetition* and uses the `frequencies` primitive to
;; gather the identical ones together with the count of how often each occurs.
;; This has the effect of determining, for each cell, how many living neighbors
;; it itself has.
(defn step [neighbors cells]
  (set (for [[loc n] (frequencies (mapcat neighbors cells))
             :when (or (= n 3) (and (= n 2) (cells loc)))]
         loc)))

;; And here is where I'm going to try to be creative: rather than having a new
;; defn for each dimension, I want a generator-function that will take `dim` as
;; a parameter and return a fn that generates all the neighbors for a given
;; point.
(defn make-neighbors-fn [dim]
  (let [surrounding (remove #{(repeat dim 0)}
                            (comb/selections [-1 0 1] dim))]
    (fn [point]
      (map #(mapv + point %) surrounding))))

(defn part-1
  "Day 17 Part 1"
  [input]
  (->> (parse-input input 1)
       (iterate (partial step (make-neighbors-fn 3)))
       (drop 6)
       first
       count))

(defn part-2
  "Day 17 Part 2"
  [input]
  (->> (parse-input input 2)
       (iterate (partial step (make-neighbors-fn 4)))
       (drop 6)
       first
       count))
