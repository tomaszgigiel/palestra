(ns pl.tomaszgigiel.palestra.codewars.mix-test
  (:use [clojure.test])
  (:require [pl.tomaszgigiel.palestra.codewars.mix :as mix])
  (:require [pl.tomaszgigiel.palestra.my-test-config :as my-test]))

(use-fixtures :once my-test/once-fixture)
(use-fixtures :each my-test/each-fixture)

(deftest a-test1
  (testing "Basic tests"
           (is (= (mix/mix "Are they here" "yes, they are here") "2:eeeee/2:yy/=:hh/=:rr"))
           (is (= (mix/mix "looping is fun but dangerous" "less dangerous than coding") "1:ooo/1:uuu/2:sss/=:nnn/1:ii/2:aa/2:dd/2:ee/=:gg"))
           (is (= (mix/mix " In many languages" " there's a pair of functions") "1:aaa/1:nnn/1:gg/2:ee/2:ff/2:ii/2:oo/2:rr/2:ss/2:tt"))
           (is (= (mix/mix "Lords of the Fallen" "gamekult") "1:ee/1:ll/1:oo"))
           (is (= (mix/mix "codewars" "codewars") ""))
           (is (= (mix/mix "A generation must confront the looming " "codewarrs") "1:nnnnn/1:ooooo/1:tttt/1:eee/1:gg/1:ii/1:mm/=:rr"))
           (is (= (mix/mix "aaa bb c" "aa bbbb c") "2:bbbb/1:aaa"))
           ))

(deftest meta-test
  (is (= (test #'mix/mix-transform-entry) :ok))
  (is (= (test #'mix/mix-transform-string) :ok))
  (is (= (test #'mix/mix-merge-entry-val) :ok))
  (is (= (test #'mix/mix-entry-to-string) :ok))
  (is (= (test #'mix/mix-keyfn) :ok))
  )
