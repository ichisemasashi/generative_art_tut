(ns generative_art_tut.gen_4_2_4
  (:require [quil.core :as q]))

;;;;; Processing用のソース;;;;;
; void setup() {
;   size(500, 300);
;   background(255);
;   strokeWeight(5);
;   smooth();
;   
;   float radius = 0;
;   float radiusnoise = random(10);
;   int centx = 250;
;   int centy = 150;
;   stroke(0, 30);
;   noFill();
;   ellipse(centx, centy, radius*2, radius*2);
;   
;   stroke(20, 50, 70);
;   strokeWeight(0.5);
;   float x1, y1, x2, y2;
;   int strokeColor = 255;
; 
;   for (float ang = 0;ang <= 360;ang += 1) {
;     strokeColor -= 1;
;     if (strokeColor < 0) {
;       strokeColor = 255;
;     }
;     stroke(strokeColor);
;     float rad = radians(ang);
;     radiusnoise += 0.005;
;     radius = (noise(radiusnoise) * 550) + 1;
;     x1 = centx + (radius * cos(rad));
;     y1 = centy + (radius * sin(rad));
;     x2 = centx + (radius * cos(rad + PI));
;     y2 = centy + (radius * sin(rad + PI));
;     line(x1, y1, x2, y2);
;   }
; }
;;;; ここまで ;;;;;
(defn- bk_gray [] (q/background 255))
(defn- circle [x y r]
  (q/ellipse x y r r))
(defn- half [x]
  (/ x 2))
(defn- radii [r]
  (+ 1 (* 550 (q/noise r))))
(defn- set-x [x r θ]
  (+ x (* (radii r) (q/cos θ))))
(defn- set-y [y r θ]
  (+ y (* (radii r) (q/sin θ))))

(defn setup []
  (bk_gray)
  (q/smooth)

  (let [d 360
        p 180
        radius-noise (range (rand 10) Float/MAX_VALUE 0.005)
        cent-x (half (q/width))
        cent-y (half (q/height))
        angles (map q/radians (range 0 d 1))
        angles2 (map q/radians (range p (+ d p) 1))
        strokeColor (cycle (range 255 -1 -1))
        x1 (map set-x (repeat cent-x) radius-noise angles)
        y1 (map set-y (repeat cent-y) radius-noise angles)
        x2 (map set-x (repeat cent-x) radius-noise angles2)
        y2 (map set-y (repeat cent-y) radius-noise angles2)
        ]
    (q/stroke 20 50 70)
    (q/stroke-weight 0.5)
    (dorun (map (fn [s x1 y1 x2 y2]
                 (q/stroke s)
                 (q/line x1 y1 x2 y2))
                strokeColor x1 y1 x2 y2)))
  (q/save-frame "gen.4.2.4.jpg"))
(q/defsketch gen_4_2_3
  :title "circle 4 before Wave Clock"
  :setup setup
  :size [500 300]
  )

