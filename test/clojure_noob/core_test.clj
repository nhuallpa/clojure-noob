(ns clojure-noob.core-test
  (:require [clojure.test :refer :all]
            [clojure-noob.core :refer :all]))

(deftest math-operations-test
  (testing "Test some examples"
    (is (= 1 1))
    (is (= 5 (+ 3 2)))
    (is (= 10 (+ 5 5)))))

(deftest map-test
  (testing "Test a map"
    (let [return (map - [1 2 3 4 5 6 7])]
      (is (= 7 (count return)))
      (is (every? neg? return)))))