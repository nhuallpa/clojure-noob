(ns clojure-noob.kata.remove-url
  (:require [clojure.test :refer :all]
            [clojure.string :as str]
            ))


(defn remove-url-anchor
  [url]
  (first (str/split url #"#")))


(deftest basic-tests
         (are [example expected] (= (remove-url-anchor example) expected)
              "www.climate.com#about" "www.climate.com"
              "www.climate.com/katas/?page=1#about" "www.climate.com/katas/?page=1"
              "www.climate.com/katas/" "www.climate.com/katas/"))