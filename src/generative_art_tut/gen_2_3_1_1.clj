(ns generative_art_tut.gen_2_3_1_1
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

(defn- half [i]
  (/ i 2))

(defn- inner-ellipse [x y r]
  (q/stroke-weight 1)
  (q/no-fill)
  (dorun (map #(q/ellipse x y % %)  (range 10 r 10))))



(defn setup []
  (setup-win))

(defn draw-my-art []
  (let [w (q/width), h (q/height)]
   (inner-ellipse (half w) (half h) 400)
  )
  ;(q/save-frame "gen.2.3.3.1.jpg")
  )

(q/defsketch gen_2_3_1_1
  :title "circle in circle"
  :size [500 300]
  :setup setup
  :draw draw-my-art
  )