(ns advent-of-code.day21
  (:require [clojure.string :as str]
            [clojure.set :as set]))

(defn- parse-line [line]
  (let [[ingr aller] (str/split (str/replace line ")" "") #" \(contains ")]
    (list (str/split ingr #" ")
          (str/split aller #", "))))

(defn- update-allers [newi newa allers]
  (reduce (fn [all a]
            (assoc all a (if (allers a)
                           (set/intersection (allers a) (set newi))
                           (set newi))))
          allers newa))

(defn- parse-data [lines]
  (reduce (fn [struct line]
            (let [[ingrs allers] (parse-line line)]
              (assoc struct
                     :ingrs (set/union (:ingrs struct) (set ingrs))
                     :allers (update-allers ingrs allers (:allers struct)))))
          {:ingrs #{}, :allers {}} lines))

(defn- is-safe? [all i]
  (if (empty? (filter #(% i) (vals all))) true false))

(defn- find-safe-occur [data]
  (let [parsed (parse-data data)
        safe   (set (filter #(is-safe? (:allers parsed) %) (:ingrs parsed)))]
    (reduce (fn [x line]
              (let [[ingrs _] (parse-line line)]
                (+ x (count (filter safe ingrs)))))
            0 data)))

(defn part-1
  "Day 21 Part 1"
  [input]
  (->> input
       str/split-lines
       find-safe-occur))

(defn- remove-from [all key]
  (let [found (first (all key))
        all   (dissoc all key)]
    (reduce (fn [all key]
              (assoc all key (disj (all key) found)))
            all (keys all))))

(defn- make-aller-map [all]
  (loop [all all, allmap {}]
    (cond
      (empty? all) allmap
      :else
      (let [single (first (filter #(= 1 (count (all %))) (keys all)))]
        (recur (remove-from all single) (assoc allmap
                                               single (first (all single))))))))

(defn- find-dangerous-list [data]
  (let [parsed (parse-data data)
        danger (make-aller-map (:allers parsed))]
    (str/join "," (map danger (sort (keys danger))))))

(defn part-2
  "Day 21 Part 2"
  [input]
  (->> input
       str/split-lines
       find-dangerous-list))
