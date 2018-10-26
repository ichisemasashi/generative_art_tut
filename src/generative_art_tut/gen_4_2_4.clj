(ns generative_art_tut.gen_4_2_4
  (:require [quil.core :as q]))

;;;;; Processing用のソース;;;;;
; float angleNoise, radiusNoise;
; float angle = -PI/2;
; float radius;
; float centX, centY;
; float strokeColor = 254;
; int strokeChange = -1;
; 
; void setup() {
;   size(500, 300);
;   smooth();
;   frameRate(30);
;   background(255);
;   noFill();
;   
;   angleNoise = random(10);
;   radiusNoise = random(10);
;   
;   centX = width/2;
;   centY = height/2;
; }
; 
; void draw() {
;   radiusNoise += 0.005;
;   radius = (noise(radiusNoise) * 550) + 1;
;   angleNoise += 0.005;
;   angle += (noise(radiusNoise) * 6) - 3;
;   if(angle > 360) {angle -= 360;}
;   if(angle < 0) {angle += 360;}
;   
;   float rad = radians(angle);
;   float x1 = centX + (radius * cos(rad));
;   float y1 = centY + (radius * sin(rad));
;   float x2 = centX + (radius * cos(rad + PI));
;   float y2 = centY + (radius * sin(rad + PI));
;   
;   strokeColor += strokeChange;
;   if(strokeColor > 254) {strokeChange = -1;}
;   if (strokeColor < 0) {strokeChange = 1;}
;   stroke(strokeColor, 60);
;   strokeWeight(1);
;   line(x1, y1, x2, y2);
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

