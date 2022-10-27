am5.ready(function() {

	// Create root element
	var root = am5.Root.new("chart-vtas-bajas");


	// Set themes
	root.setThemes([
		am5themes_Animated.new(root)
	]);


	// Create chart
	var chart = root.container.children.push(am5xy.XYChart.new(root, {
		panX: false,
		panY: false,
		wheelX: "panX",
		wheelY: "zoomX",
		layout: root.verticalLayout
	}));


	// Add legend
	var legend = chart.children.push(
		am5.Legend.new(root, {
			centerX: am5.p50,
			x: am5.p50
		})
	);

	var data = dataBar;


	// Create axes
	var xAxis = chart.xAxes.push(am5xy.CategoryAxis.new(root, {
		categoryField: "platform",
		renderer: am5xy.AxisRendererX.new(root, {
			cellStartLocation: 0.1,
			cellEndLocation: 0.9
		}),
		tooltip: am5.Tooltip.new(root, {})
	}));

	xAxis.data.setAll(data);
	chart.get("colors").set("colors", [
		am5.color("#0077b5"),
		am5.color("#be1212")
	]);

	var yAxis = chart.yAxes.push(am5xy.ValueAxis.new(root, {
		renderer: am5xy.AxisRendererY.new(root, {})
	}));


	// Add series
	function makeSeries(name, fieldName) {
		var series = chart.series.push(am5xy.ColumnSeries.new(root, {
			name: name,
			xAxis: xAxis,
			yAxis: yAxis,
			valueYField: fieldName,
			categoryXField: "platform"
		}));

		series.columns.template.setAll({
			tooltipText: "{name}, {categoryX}:{valueY}",
			width: am5.percent(90),
			tooltipY: 0
		});

		series.data.setAll(data);

		// Make stuff animate on load
		series.appear();

		series.bullets.push(function() {
			return am5.Bullet.new(root, {
				locationY: 0,
				sprite: am5.Label.new(root, {
					text: "{valueY}",
					fill: root.interfaceColors.get("alternativeText"),
					centerY: 0,
					centerX: am5.p50,
					populateText: true
				})
			});
		});

		legend.data.push(series);
	}

	makeSeries("Ventas", "ventas", "#aa0000");
	makeSeries("Aprovisionamiento", "aprov", "#ffffff");


	// Make stuff animate on load
	chart.appear(1000, 100);

}); // end am5.ready()