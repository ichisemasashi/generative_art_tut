(ns generative_art_tut.gen_2_2_1
  (:require [quil.core :as q]
            [quil.middleware :as m]))

(defn- bk_gray [] (let [g 230] (q/background g g g)))
(defn- sk_red [] (let [r 130, z 0] (q/stroke r z z)))
(defn- sk_4 [] (let [c 4] (q/stroke-weight c)))
(defn- fill_el [] (let [a 255, b 150] (q/fill a b)))
(defn- setup-win []
  ; size() -- not support, use :size[].
  (q/smooth)
  (bk_gray))
(defn- setup-line []
  (sk_red)
  (sk_4))
(defn- setup-ellips []
  (fill_el))
(defn setup []
  (setup-win)
  (setup-line)
  (setup-ellips))
(defn- half [i]
  (/ i 2))
(defn- closs-the-lines [w h]
  (let [w (half w), h (half h), d 70]
    (q/line (- w d) (- h d) (+ w d) (+ h d))  ; \
    (q/line (+ w d) (- h d) (- w d) (+ h d)))); /
(defn- draw-the-circle [w h]
  (let [w (half w), h (half h), d 50]
    (q/ellipse w h d d)))



(defn draw-my-art []
  (let [w (q/width), h (q/height)]
    (draw-the-circle w h)
    (closs-the-lines w h))
  (q/save-frame "gen.2.2.1.jpg"))

(q/defsketch gen_2_2_1
  :title "Cross with circle 2"
  :size [500 300]
  :setup setup
  :draw draw-my-art
  )