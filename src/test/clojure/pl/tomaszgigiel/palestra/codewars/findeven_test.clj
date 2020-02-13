(ns pl.tomaszgigiel.palestra.codewars.findeven-test
  (:use [clojure.test])
  (:require [pl.tomaszgigiel.palestra.codewars.findeven :as findeven])
  (:require [pl.tomaszgigiel.palestra.my-test-config :as my-test]))

(use-fixtures :once my-test/once-fixture)
(use-fixtures :each my-test/each-fixture)

(deftest ok-test
  (testing "find-even-index"
           (is (= (findeven/find-even-index [1,2,3,4,3,2,1]) 3))
           (is (= (findeven/find-even-index [1,100,50,-51,1,1]) 1))
           (is (= (findeven/find-even-index [1,2,3,4,5,6]) -1))
           (is (= (findeven/find-even-index [20,10,30,10,10,15,35]), 3))
           (is (= (findeven/find-even-index [20,10,-80,10,10,15,35]), 0))
           (is (= (findeven/find-even-index [1,2,3,4,5,-15,99]), 6))))
