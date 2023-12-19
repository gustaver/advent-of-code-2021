(ns solutions.five
  (:require [clojure.string :as str]
            [utils :as u]))

(defn print-lines [lines]
  (let [axes (vec (repeat 10 (vec (repeat 10 0))))]
    (reduce (fn [acc [p c]] (assoc-in acc p c)) axes lines)))

(defn parse-pair [pair]
  (as-> pair $ 
    (str/split $ #" -> ")
    (mapv (fn [p]
            (->> (u/split-comma p)
                 (mapv u/str->int))) $)))

(defn parse-input [input]
  (let [lines (str/split-lines input)]
    (->> lines
        (map parse-pair))))

(defn mark-line [lines [[x1 y1] [x2 y2]]] 
  (let [[x1 x2] (sort [x1 x2])
        [y1 y2] (sort [y1 y2])
        points (for [x (range x1 (inc x2))
                     y (range y1 (inc y2))]
                 [x y])] 
    (reduce (fn [acc p] (update acc p (fn [c] (if c (inc c) 1)))) lines points)))

(defn solve [input]
  (let [pairs (parse-input input)
        filtered-pairs (filter (fn [[[x1 y1] [x2 y2]]] (or (= x1 x2) (= y1 y2))) pairs)]
    (->> (reduce mark-line {} filtered-pairs)
         vals
         (u/count-cond (fn [v] (> v 1))))))