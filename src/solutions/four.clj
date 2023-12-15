(ns solutions.four
  (:require [clojure.string :as str]
            [utils :as u]))

(defn columns [board]
  (for [c (range (count (first board)))]
    (map #(nth % c) board)))

(defn mark-board [board n]
  (map (fn [r] (map (fn [x] (if (= n x) :marked x)) r)) board))

(defn winner? [board]
  (some u/all=? (concat board (columns board))))

(defn get-winner [boards]
  (u/find-first winner? boards))

(defn play-bingo [x y]
  (loop [boards x
         [n & rest] y] 
    (let [boards (map #(mark-board % n) boards)
          winner (get-winner boards)] 
      (if winner
        [winner n]
        (recur boards rest)))))

(defn parse-board [board]
  (->> board
       str/split-lines
       (map (comp u/split-whitespace str/trim))
       (map (fn [r] (mapv u/str->int r)))))

(defn parse-input [input]
  (let [[numbers & boards ] (-> input
                                (str/split #"\n\n"))
        boards (map parse-board boards)
        numbers (->> (str/split numbers #",")
                     (map u/str->int))]
    [numbers boards]))

(defn solve [input]
  (let [[numbers boards] (parse-input input)
        [winner n] (play-bingo boards numbers)]
    [winner n]
    [(->> winner
          flatten
          (filter (complement keyword?)) 
          u/sum
          (* n))]))
