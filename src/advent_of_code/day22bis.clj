(ns advent-of-code.day22bis
  (:require [clojure.string :as str]))

(defn- get-input [func input]
  (-> input
      (str/split #"\n\n")
      func))

(defn- parse-data [[p1 p2]]
  (let [[_ & lines1] (str/split-lines p1)
        [_ & lines2] (str/split-lines p2)]
    [(map read-string lines1) (map read-string lines2)]))

(defn- play-game [[p1 p2]]
  (loop [[n1 & p1] p1, [n2 & p2] p2]
    (cond
      (nil? n1) {:deck (cons n2 p2)}
      (nil? n2) {:deck (cons n1 p1)}
      (< n1 n2) (recur p1 (concat p2 (list n2 n1)))
      :else     (recur (concat p1 (list n1 n2)) p2))))

(defn- score-game [{deck :deck}]
  (reduce + (map * deck (range (count deck) 0 -1))))

(defn part-1
  "Day 22 Part 1"
  [input]
  (->> input
       (get-input parse-data)
       play-game
       score-game))

(defn- play-game-rec [[p1 p2]]
  (loop [p1 p1, p2 p2, seen #{}]
    (if (seen [p1 p2])
      {:winner :p1, :deck p1}
      (let [[n1 & p1'] p1, [n2 & p2'] p2, seen (conj seen [p1 p2])]
        (cond
          (nil? n1) {:winner :p2, :deck p2}
          (nil? n2) {:winner :p1, :deck p1}
          (and (<= n1 (count p1'))
               (<= n2 (count p2')))
          (let [game' (play-game-rec [(take n1 p1') (take n2 p2')])]
            (case (:winner game')
              :p1 (recur (concat p1' (list n1 n2)) p2' seen)
              :p2 (recur p1' (concat p2' (list n2 n1)) seen)))
          (< n1 n2) (recur p1' (concat p2' (list n2 n1)) seen)
          :else     (recur (concat p1' (list n1 n2)) p2' seen))))))

(defn part-2
  "Day 22 Part 2"
  [input]
  (->> input
       (get-input parse-data)
       play-game-rec
       score-game))
