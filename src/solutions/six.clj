(ns solutions.six
  (:require [utils :as u]))

(defn tick [fish]
  (if (zero? fish)
    [6 8]
    (dec fish)))

(defn parse-input [input]
  (->> input
       u/split-comma
       (map u/str->int)))

(defn solve [input]
  (let [fish-initial (parse-input input)
        days 80]
    (->> (reduce (fn [fish _]
                   (->> fish
                        (map tick)
                        flatten)) fish-initial (range days))
         count)))
