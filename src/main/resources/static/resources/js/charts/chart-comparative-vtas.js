am5.ready(function() {

	// Create root element
	var root = am5.Root.new("chart-comparative-vtas");

	// Set themes
	root.setThemes([
		am5themes_Animated.new(root)
	]);

	// Create chart
	var chart = root.container.children.push(
		am5xy.XYChart.new(root, {
			layout: root.verticalLayout,
			pinchZoomX: true
		})
	);

	// Add cursor
	var cursor = chart.set("cursor", am5xy.XYCursor.new(root, {
		behavior: "none"
	}));
	cursor.lineY.set("visible", false);

	// The data
	var data = comparative;

	// Create axes
	var xRenderer = am5xy.AxisRendererX.new(root, {});
	xRenderer.grid.template.set("location", 0.5);
	xRenderer.labels.template.setAll({
		location: 0.5,
		multiLocation: 0.5
	});

	var xAxis = chart.xAxes.push(
		am5xy.CategoryAxis.new(root, {
			categoryField: "date",
			renderer: xRenderer,
			tooltip: am5.Tooltip.new(root, {})
		})
	);

	xAxis.data.setAll(data);
	chart.get("colors").set("colors", [
		am5.color("#0077b5"),
		am5.color("#46ab09")
	]);
	
	var yAxis = chart.yAxes.push(
		am5xy.ValueAxis.new(root, {
			maxPrecision: 0,
			renderer: am5xy.AxisRendererY.new(root, {
				inversed: false
			})
		})
	);

	// Add series

	function createSeries(name, field) {
		var series = chart.series.push(
			am5xy.LineSeries.new(root, {
				name: name,
				xAxis: xAxis,
				yAxis: yAxis,
				valueYField: field,
				categoryXField: "date",
				tooltip: am5.Tooltip.new(root, {
					pointerOrientation: "horizontal",
					labelText: "[bold]{name}[/]\n{categoryX}: {valueY}"
				})
			})
		);


		series.bullets.push(function() {
			return am5.Bullet.new(root, {
				sprite: am5.Circle.new(root, {
					radius: 5,
					fill: series.get("fill")
				})
			});
		});

		// create hover state for series and for mainContainer, so that when series is hovered,
		// the state would be passed down to the strokes which are in mainContainer.
		series.set("setStateOnChildren", true);
		series.states.create("hover", {});

		series.mainContainer.set("setStateOnChildren", true);
		series.mainContainer.states.create("hover", {});

		series.strokes.template.states.create("hover", {
			strokeWidth: 4
		});

		series.data.setAll(data);
		series.appear(1000);
	}

	createSeries("Ventas", "ventas");
	createSeries("Aprovisionamientos", "aprov");

	// Add scrollbar
	chart.set("scrollbarX", am5.Scrollbar.new(root, {
		orientation: "horizontal",
		marginBottom: 20
	}));

	var legend = chart.children.push(
		am5.Legend.new(root, {
			centerX: am5.p50,
			x: am5.p50
		})
	);

	// Make series change state when legend item is hovered
	legend.itemContainers.template.states.create("hover", {});

	legend.itemContainers.template.events.on("pointerover", function(e) {
		e.target.dataItem.dataContext.hover();
	});
	legend.itemContainers.template.events.on("pointerout", function(e) {
		e.target.dataItem.dataContext.unhover();
	});

	legend.data.setAll(chart.series.values);

	// Make stuff animate on load
	chart.appear(1000, 100);

}); // end am5.ready()