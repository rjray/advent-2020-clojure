(ns advent-of-code.day16
  (:require [clojure.string :as str]
            [clojure.set :as set]))

(defn- get-input [func input]
  (->> input
       str/split-lines
       func))

(defn- process-fields [fields]
  (reduce (fn [fs line]
            (let [[_ name r1 r2 r3 r4]
                  (re-find #"([\w ]+): (\d+)-(\d+) or (\d+)-(\d+)" line)]
              (assoc fs name (concat (range (Integer/parseInt r1)
                                            (inc (Integer/parseInt r2)))
                                     (range (Integer/parseInt r3)
                                            (inc (Integer/parseInt r4)))))))
          {} fields))

(defn- parse-data [lines]
  (loop [[line & lines] lines,
         data           {:fields (), :yours (), :tickets ()},
         key            :fields,
         keys           (list :yours :tickets)]
    (cond
      (nil? line) {:fields  (process-fields (data :fields))
                   :yours   (first (data :yours))
                   :tickets (butlast (data :tickets))}
      (= line "") (recur lines data (first keys) (rest keys))
      :else       (recur lines (assoc data key (cons line (data key)))
                         key keys))))

(defn- find-error-rate [data]
  (let [union  (set (apply concat (vals (data :fields))))
        values (map #(Integer/parseInt %)
                    (re-seq #"\d+" (str/join "," (data :tickets))))]
    (reduce + (filter #(not (contains? union %)) values))))

(defn part-1
  "Day 16 Part 1"
  [input]
  (->> input
       (get-input parse-data)
       (find-error-rate)))

(defn- to-nums [v] (map #(Integer/parseInt %) v))

(defn- find-valid-tickets [data]
  (let [union   (set (apply concat (vals (data :fields))))
        tickets (->> (map #(str/split % #",") (data :tickets))
                     (map to-nums))]
    (filter #(every? union %) tickets)))

(defn- all-candidates [choices tickets pos]
  (let [nums (map #(nth % pos) tickets)]
    (reduce (fn [lst choice]
              (if (empty? (filter #(not (contains? (choices choice) %)) nums))
                (cons choice lst) lst))
            () (keys choices))))

(defn- derive-candidates [needed open tickets fields]
  (let [choices (reduce (fn [c k]
                          (assoc c k (fields k)))
                        {} needed)]
    (reduce (fn [picks pos]
              (assoc picks pos (all-candidates choices tickets pos)))
            {} open)))

(defn- find-fields [fields tickets]
  (let [length    (count (keys fields))
        allfields (set (keys fields))
        fieldsvec (vec (repeat length nil))]
    (loop [fv fieldsvec, found #{}]
      (cond
        (= found allfields) fv
        :else
        (let [needed     (set/difference allfields (set fv))
              open       (filter #(nil? (fv %)) (range length))
              candidates (derive-candidates needed open tickets fields)
              singles    (filter #(= 1 (count (candidates %))) open)
              newfv      (reduce (fn [fv' pos]
                                   (assoc fv' pos (first (candidates pos))))
                                 fv singles)
              newfound   (reduce (fn [s pos]
                                   (conj s (first (candidates pos))))
                                 found singles)]
          (recur newfv newfound))))))

(defn- id-ticket [yours guide]
  (zipmap guide yours))

(defn- get-departure-keys [ticket]
  (reduce (fn [tic key]
            (if (re-matches #"^departure.*" key)
              (assoc tic key (ticket key))
              tic))
          {} (keys ticket)))

(defn- find-departure-values [data]
  (let [tickets (find-valid-tickets data)
        fields  (reduce (fn [fs field]
                          (assoc fs field (set ((data :fields) field))))
                        {} (keys (data :fields)))
        yours   (map #(Integer/parseInt %) (re-seq #"\d+" (data :yours)))]
    (->> (find-fields fields tickets)
         (id-ticket yours)
         get-departure-keys
         vals)))

(defn part-2
  "Day 16 Part 2"
  [input]
  (->> input
       (get-input parse-data)
       find-departure-values
       (apply *)))
