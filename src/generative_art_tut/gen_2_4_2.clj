(ns generative_art_tut.gen_2_4_2
  (:require [quil.core :as q]
            [quil.helpers.seqs :as h]))

;;;;; Processing用のソース;;;;;
; int diam = 10;
; float centX, centY;
; 
; void setup() {
;   size(500, 300);
;   frameRate(24);
;   
;   smooth();
;   background(180);
;   centX = width/2;
;   centY = height/2;
;   strokeWeight(1);
;   noFill();
; }
; 
; void draw() {
;   if(diam <= 400) {
;     // background(180);
;     ellipse(centX, centY, diam, diam);
;     diam += 10;
;   }
; }
;;;; ここまで ;;;;;

(defn- bk_gray [] (q/background 180))
(defn- setup-win [] 
  (q/frame-rate 24)
  (q/smooth)
  (bk_gray))
(defn- setup-circle []
  (q/stroke 0)
  (q/stroke-weight 1)
  (q/no-fill))
(defn- half [n] (/ n 2))
(defn- setup-state [] 
;  (let [diams (h/range-incl 10 400 10)]
  (q/set-state! :diam (h/seq->stream (range 10 400 10))
                :cent-x (q/floor (half (q/width)))
                :cent-y (q/floor (half (q/height)))))
(defn- inner-ellipse [x y r]
  (q/stroke-weight 1)
  (dorun (map #(q/ellipse x y % %)  (range 10 r 10))))
(defn- draw-out-ellipse [x y r]
  ; (bk_gray)
  (q/ellipse x y r r))

(defn setup []
  (setup-win)
  (setup-circle)
  (setup-state))

(defn draw []
  (let [cent-x (q/state :cent-x)
        cent-y (q/state :cent-y)
        diam   ((q/state :diam))]
    (when diam
      (draw-out-ellipse cent-x cent-y diam)))
      ; (inner-ellipse cent-x cent-y diam)))
  (q/save-frame "gen.2.4.2.jpg"))

(q/defsketch gen_2_4_2
  :title "Growing circle and inner"
  :setup setup
  :draw draw
  :size [500 300]
  ;:keep-on-top true
  )

