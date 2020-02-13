(ns pl.tomaszgigiel.palestra.codewars.third-angle-of-triangle-test
  (:use [clojure.test])
  (:require [pl.tomaszgigiel.palestra.codewars.third-angle-of-triangle :as third-angle-of-triangle])
  (:require [pl.tomaszgigiel.palestra.my-test-config :as my-test]))

(use-fixtures :once my-test/once-fixture)
(use-fixtures :each my-test/each-fixture)

(deftest a-test1
  (testing "Test 1"
           (def a 30)
           (def b 60)
           (def c 90)
           (is (= (third-angle-of-triangle/third-angle a b) c))))
