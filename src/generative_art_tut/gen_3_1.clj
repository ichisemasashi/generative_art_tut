(ns generative_art_tut.gen_3_1
  (:require [quil.core :as q]
            [quil.helpers.seqs :as h]))

;;;;; Processing用のソース;;;;;
; size(500, 100);
; background(255);
; strokeWeight(5);
; smooth();
; 
; stroke(20, 50, 70);
; line(20,50,480,50);
;;;; ここまで ;;;;;

(defn- bk_w [] (q/background 255))
(defn- setup-line []
  (q/stroke-weight 5)
  (q/stroke 20 50 70))
(defn setup []
  (bk_w)
  (q/smooth)
  (setup-line)
  (q/line 20 50 480 50)
  (q/save-frame "gen.3.1.jpg"))

(q/defsketch gen_3_1
  :title "Strait Lines"
  :setup setup
  :size [500 100]
  )

