(ns clojure-noob.kata.first-number-odd
  (:require [clojure.test :refer :all]
            [clojure.string :as str]
            ))

(defn find-odd
  [numbers]
  (first (keys (filter (fn [[key value]] (odd? value)) (frequencies numbers)))))


(deftest find-odd-tests
         (are [xs answer] (= (find-odd xs) answer)
                          [5 4 3 2 1 5 4 3 2 10 10] 1
                          [1 1 1 1 1 1 10 1 1 1 1] 10
                          [20 1 1 2 2 3 3 5 5 4 20 4 5] 5
                          [1 1 2 -2 5 2 4 4 -1 -2 5] -1
                          [20 1 -1 2 -2 3 3 5 5 1 2 4 20 4 -1 -2 5] 5
                          [1 1] nil
                          [0 7 7] 0
                          [7] 7
                          [0] 0
                          ))