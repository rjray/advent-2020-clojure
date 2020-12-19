(ns advent-of-code.day19
  (:require [clojure.string :as str]
            [instaparse.core :as insta]))

(defn- massage [line]
  (str/replace (str/replace line #"\d+" #(str "R" %1))
               #":" " = "))

(defn- make-grammar [rules]
  (let [rules (str/split-lines rules)]
    (str/join "\n"
              (reduce (fn [lst line]
                        (conj lst (massage line)))
                      ["S = R0"] rules))))

(defn- match-messages [[rules data]]
  (let [grammar (make-grammar rules)
        parser  (insta/parser grammar)]
    (filter sequential? (map parser (str/split-lines data)))))

(defn part-1
  "Day 19 Part 1"
  [input]
  (-> input
      (str/split #"\n\n")
      match-messages
      count))

(defn- fixup-rules [[rules data]]
  (let [replace {"8" "8: 42 | 42 8"
                 "11" "11: 42 31 | 42 11 31"}]
    (list (str/join "\n"
                    (reduce (fn [lst line]
                              (let [[k] (str/split line #":")]
                                (conj lst (if (replace k)
                                            (replace k) line))))
                            [] (str/split-lines rules)))
          data)))

(defn part-2
  "Day 19 Part 2"
  [input]
  (-> input
      (str/split #"\n\n")
      fixup-rules
      match-messages
      count))
