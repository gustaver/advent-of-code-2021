(ns solutions.two
  (:require [clojure.string :as str]
            [utils :refer [str->int]]))

(defn input->commands [input]
  (->> input
       (str/split-lines)
       (map (fn [s] (str/split s #" ")))
       (map (fn [[c x]] {:command c :val (str->int x)}))))

(defn solve [input]
  (let [commands (input->commands input)]
    {:p1 (let [{h :horizonal d :depth} (->> commands
                                            (reduce (fn [acc {c :command v :val}]
                                                      (case c
                                                        "forward" (update acc :horizonal + v)
                                                        "down" (update acc :depth + v)
                                                        "up" (update acc :depth - v))) {:horizonal 0 :depth 0}))]
           (* h d))
     :p2 (let [{h :horizonal d :depth} (->> commands
                                            (reduce (fn [{aim :aim :as acc} {c :command v :val}]
                                                      (case c
                                                        "forward" (-> (update acc :horizonal + v)
                                                                      (update :depth (fn [d] (+ d (* v aim)))))
                                                        "down" (update acc :aim + v)
                                                        "up" (update acc :aim - v))) {:horizonal 0 :depth 0 :aim 0}))]
           (* h d))}))