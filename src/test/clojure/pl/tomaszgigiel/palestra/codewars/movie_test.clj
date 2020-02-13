(ns pl.tomaszgigiel.palestra.codewars.movie-test
  (:use [clojure.test])
  (:require [pl.tomaszgigiel.palestra.codewars.movie :as movie])
  (:require [pl.tomaszgigiel.palestra.my-test-config :as my-test]))

(use-fixtures :once my-test/once-fixture)
(use-fixtures :each my-test/each-fixture)

(deftest ok-test
  (testing "movie"
           (is (= (movie/movie 500, 15, 0.9) 43))
           (is (= (movie/movie 100, 10, 0.95) 24))
           ))
