(ns core
  (:require [solutions.one :refer [solve]]))

(defn -main [_]
  (let [input (slurp "inputs/01.in")]
    (println (solve input))))