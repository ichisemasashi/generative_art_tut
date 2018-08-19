(ns generative_art_tut.gen_2_2_6
  (:require [quil.core :as q]
            [quil.middleware :as m]))


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
(defn- draw-the-quad [w h]
  (let [d 50, x1 (half (- w d)), y1 (half (- h d))
              x2 (half (+ w d)), y2 (half (- h d))
              x3 (half (+ w d)), y3 (half (+ h d))
              x4 (half (- w d)), y4 (half (+ h d))]
    (sk_black)
    (sk 6)
    (fill_el)
    (q/quad x1 y1 x2 y2 x3 y3 x4 y4)))



(defn draw-my-art []
  (let [w (q/width), h (q/height)]
    (closs-the-lines w h)
    (draw-the-quad w h)
  )
  ;(q/save-frame "gen.2.2.5.jpg")
  )

(q/defsketch gen_2_2_6
  :title "Cross with circle 7"
  :size [500 300]
  :setup setup
  :draw draw-my-art
  )