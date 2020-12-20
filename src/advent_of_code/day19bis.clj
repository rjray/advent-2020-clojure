(ns advent-of-code.day19bis
  (:require [clojure.string :as str]
            [instaparse.core :as insta]))

;; First "optimization": Turns out that Instaparse didn't need me to massage
;; the grammar after all. It just needed to be told what the starting rule
;; is.
(defn- match-messages [[grammar data]]
  (let [parser (insta/parser grammar :start :0)]
    (filter sequential? (map parser (str/split-lines data)))))

(defn part-1
  "Day 19 Part 1"
  [input]
  (-> input
      (str/split #"\n\n")
      match-messages
      count))

;; Credit for the tip on this optimization goes to "misha" on the Clojure Slack:
(defn- fixup [data]
  (reduce-kv str/replace data
             {"8: 42"     "8: 42 | 42 8"
              "11: 42 31" "11: 42 31 | 42 11 31"}))

(defn part-2
  "Day 19 Part 2"
  [input]
  (-> input
      fixup
      (str/split #"\n\n")
      match-messages
      count))
