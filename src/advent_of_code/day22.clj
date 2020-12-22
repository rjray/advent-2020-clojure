(ns advent-of-code.day22
  (:require [clojure.string :as str]))

(defn- get-input [func input]
  (-> input
      (str/split #"\n\n")
      func))

(defn- parse-data [[p1 p2]]
  (let [[_ & lines1] (str/split-lines p1)
        [_ & lines2] (str/split-lines p2)]
    {:p1 (map read-string lines1)
     :p2 (map read-string lines2)}))

(defn- play-game [game]
  (let [p1 (:p1 game)
        p2 (:p2 game)]
    (loop [[n1 & p1] p1, [n2 & p2] p2]
      (cond
        (nil? n1) {:winner (cons n2 p2)}
        (nil? n2) {:winner (cons n1 p1)}
        (< n1 n2) (recur p1 (concat p2 (list n2 n1)))
        :else     (recur (concat p1 (list n1 n2)) p2)))))

(defn- score-game [game]
  (let [deck (:winner game)]
    (reduce + (map * (reverse deck) (iterate inc 1)))))

(defn part-1
  "Day 22 Part 1"
  [input]
  (->> input
       (get-input parse-data)
       play-game
       score-game))

(defn- play-game-rec [game]
  (let [p1 (:p1 game)
        p2 (:p2 game)]
    (loop [p1 p1, p2 p2, seen #{}]
      (if (seen (list p1 p2))
        {:winner :p1, :p1 p1}
        (let [[n1 & p1'] p1, [n2 & p2'] p2]
          (cond
            (nil? n1) {:winner :p2, :p2 p2}
            (nil? n2) {:winner :p1, :p1 p1}
            (and (<= n1 (count p1'))
                 (<= n2 (count p2')))
            (let [game' (play-game-rec {:p1 (take n1 p1')
                                        :p2 (take n2 p2')})]
              (case (:winner game')
                :p1 (recur (concat p1'
                                   (list n1 n2))
                           p2'
                           (conj seen (list p1 p2)))
                :p2 (recur p1'
                           (concat p2'
                                   (list n2 n1))
                           (conj seen (list p1 p2)))))
            (< n1 n2) (recur p1'
                             (concat p2'
                                     (list n2 n1))
                             (conj seen (list p1 p2)))
            :else     (recur (concat p1'
                                     (list n1 n2))
                             p2'
                             (conj seen (list p1 p2)))))))))

(defn- score-game-rec [game]
  (score-game {:winner (game (:winner game))}))

(defn part-2
  "Day 22 Part 2"
  [input]
  (->> input
       (get-input parse-data)
       play-game-rec
       score-game-rec))
