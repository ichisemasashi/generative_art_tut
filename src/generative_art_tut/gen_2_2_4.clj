(ns generative_art_tut.gen_2_2_4
  (:require [quil.core :as q]
            [quil.middleware :as m]))

;;; Processingのソース
;; size(500, 300);
;; smooth();
;; background(230, 230, 230);
;; 
;; stroke(130, 0, 0);
;; strokeWeight(1);
;; line(width/2 - 70, height/2 - 70, width/2 + 70, height/2 + 70);
;; line(width/2 + 70, height/2 - 70, width/2 - 70, height/2 + 70);
;; 
;; stroke(0, 125);
;; strokeWeight(6);
;; fill(255, 150);
;; ellipse(width/2, height/2, 50, 50);


(defn- bk_gray [] (let [g 230] (q/background g g g)))
(defn- sk_red [] (let [r 130, z 0] (q/stroke r z z)))
(defn- sk_black [] (q/stroke 0 125)) ;; black , alpha=50%.
(defn- sk [w] (q/stroke-weight w))
(defn- fill_el [] (let [a 255, b 150] (q/fill a b)))
(defn- setup-win []
  ; size() -- not support, use :size[].
  (q/smooth)
  (bk_gray))

(defn setup []
  (setup-win))
(defn- half [i]
  (/ i 2))
(defn- closs-the-lines [w h]
  (let [w (half w), h (half h), d 70]
    (sk_red)
    (sk 1)
    (q/line (- w d) (- h d) (+ w d) (+ h d))  ; \
    (q/line (+ w d) (- h d) (- w d) (+ h d)))); /
(defn- draw-the-circle [w h]
  (let [w (half w), h (half h), d 50]
    ;(sk_black)
    ;(sk 6)
    (q/no-stroke)
    (fill_el)
    (q/ellipse w h d d)))



(defn draw-my-art []
  (let [w (q/width), h (q/height)]
    (closs-the-lines w h)
    (draw-the-circle w h)
  )
  ;(q/save-frame "gen.2.2.4.jpg")
  )

(q/defsketch gen_2_2_4
  :title "Cross with circle 5"
  :size [500 300]
  :setup setup
  :draw draw-my-art
  )