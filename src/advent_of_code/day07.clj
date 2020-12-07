(ns advent-of-code.day07
  (:require [clojure.string :as str]))

(defn- map-contents [contents]
  (cond
    (= (first contents) "no other bags.") {}
    :else
    (reduce (fn [m content]
              (let [[_ n type] (re-find #"(\d+) (.*) bags?[.]?" content)]
                (assoc m type (Integer/parseInt n))))
            {} contents)))

(defn- parse-rules [input]
  (->> input
       str/split-lines
       (map #(str/split % #" bags contain "))
       (reduce (fn [m pair]
                 (let [contents (str/split (last pair) #", ")]
                   (assoc m (first pair) (map-contents contents))))
               {})))

(defn- can-contain? [rules choice target]
  ;;(prn "can-contain:" choice (rules choice))
  (cond
    (empty? (rules choice)) false
    ((rules choice) target) true
    :else
    (if (empty? (filter #(can-contain? rules % target) (keys (rules choice))))
      false true)))

(defn- find-colors [rules target]
  (let [choices (keys rules)]
    (loop [[choice & choices] choices, colors ()]
      (cond
        (nil? choice)           colors
        (empty? (rules choice)) (recur choices colors)
        (can-contain?
         rules choice target)   (recur choices
                                       (cons choice colors))
        :else                   (recur choices colors)))))

(defn part-1
  "Day 07 Part 1"
  [input]
  (-> input
      parse-rules
      (find-colors "shiny gold")
      count))

(defn- inside [rules target]
  (cond
    (empty? (rules target)) 1
    :else
    (inc (apply + (map #(* (inside rules %)
                           ((rules target) %)) (keys (rules target)))))))

(defn part-2
  "Day 07 Part 2"
  [input]
  (-> input
      parse-rules
      (inside "shiny gold")
      dec))
