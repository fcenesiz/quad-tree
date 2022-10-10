### This can use for collision detection optimization or something else.

![](quadtree.png )

- ![#c5f015](https://via.placeholder.com/15/c5f015/c5f015.png) `points`
- ![#00ff00](https://via.placeholder.com/15/00ff00/00ff00.png) `queried points`

Example of usage

```Java
        List<QuadTreePoint> points = new ArrayList<>();
        for (int i = 0; i < 500; i++) {
            QuadTreePoint p = new QuadTreePoint(
                    MathUtils.random() * 500f, // x
                    MathUtils.random() * 500f // y
            );
            points.add(p);
        }

        quadTreeController = new QuadTreeController(
                0, 0, // x, y
                500, 500, // width, height
                points,
                4 // capacity
        );

        QuadTreeRectangle range = new QuadTreeRectangle(225, 375, 107, 75);
        quadTreeController.query(range);
```