(ns advent-of-code.day23
  (:require [clojure.string :as str]))

(defn- get-input [func input]
  (-> input
      str/trimr
      (str/split #"")
      func))

(defn- parse-data [lines]
  (map #(Integer/parseInt %) lines))

(defn- find-destination [stack]
  (let [dest (dec (first stack))]
    (loop [dest dest]
      (let [idx (.indexOf stack dest)]
        (case idx
          -1 (recur (mod (dec dest) 10))
          idx)))))

(defn- adjust [state]
  (let [[current & remain] state
        pickup             (take 3 remain)
        the-rest           (drop 3 remain)
        stack              (cons current the-rest)
        idx                (find-destination stack)]
    (concat (take idx the-rest)
            pickup
            (drop idx the-rest)
            (list current))))

(defn- run-game [n start]
  (reduce (fn [state n] (adjust state)) start (range n)))

(defn- get-answer [final]
  (let [nums (concat final final)]
    (str/join (take 8 (drop (inc (.indexOf nums 1)) nums)))))

(defn part-1
  "Day 23 Part 1"
  [input]
  (->> input
       (get-input parse-data)
       (run-game 100)
       get-answer))

(defn- pad-input [input]
  (concat input (range 10 1000001)))

(defn- make-linked [input]
  (let [cnt    (count input)
        linked (vec (repeat (inc cnt) nil))]
    (assoc (reduce (fn [lnkd [a b]]
                     (assoc lnkd a b))
                   linked (partition 2 1 input))
           (last input) (first input)
           0 (first input))))

(defn- find-destination-2 [val seen]
  (loop [val val]
    (cond
      (seen val) (recur (mod (dec val) 1000001))
      :else      val)))

(defn- dump [state]
  (let [cur  (first state)
        nmap (zipmap (range 1 (count state)) (rest state))
        unseen (set state)]
    (loop [idx cur, unseen unseen, lst ()]
      (case unseen
        #{} (reverse lst)
        (recur (state idx) (disj unseen idx) (cons idx lst))))))

(defn- adjust-2 [state]
  (let [cur  (state 0)
        a    (state cur)
        b    (state a)
        c    (state b)
        n    (state c)
        cval (find-destination-2 (dec cur) #{a b c 0})
        ins  (state cval)]
    (assoc state 0 n, cur n, cval a, c ins)))

(defn- run-game-2 [n start]
  (reduce (fn [state _] (adjust-2 state)) start (range n)))

(defn- get-new-answer [game]
  [(game 1) (game (game 1))])

(defn part-2
  "Day 23 Part 2"
  [input]
  (->> input
       (get-input parse-data)
       pad-input
       make-linked
       (run-game-2 10000000)
       get-new-answer
       (apply *)))
