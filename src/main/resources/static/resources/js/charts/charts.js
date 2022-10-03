am5.ready(function() {

	// Create root element
	// https://www.amcharts.com/docs/v5/getting-started/#Root_element
	var root = am5.Root.new("chartdiv");


	// Set themes
	// https://www.amcharts.com/docs/v5/concepts/themes/
	root.setThemes([
		am5themes_Animated.new(root)
	]);


	// Create chart
	// https://www.amcharts.com/docs/v5/charts/xy-chart/
	var chart = root.container.children.push(am5xy.XYChart.new(root, {
		panX: false,
		panY: false,
		wheelX: "panX",
		wheelY: "zoomX",
		layout: root.verticalLayout
	}));


	// Data
	var colors = chart.get("colors");

	var data = [{
		servicio: "Netflix",
		visits: 725,
		icon: "./resources/img/streaming_sprite/001-netflix.svg",
		columnSettings: { fill: colors.next() }
	}, {
		servicio: "Disney",
		visits: 625,
		icon: "./resources/img/streaming_sprite/002-disney-logo.svg",
		columnSettings: { fill: colors.next() }
	}, {
		servicio: "Start+",
		visits: 602,
		icon: "./resources/img/streaming_sprite/003-star-logo.svg",
		columnSettings: { fill: colors.next() }
	}, {
		servicio: "Hbo+",
		visits: 509,
		icon: "./resources/img/streaming_sprite/004-hbo.svg",
		columnSettings: { fill: colors.next() }
	}, {
		servicio: "Prime",
		visits: 422,
		icon: "./resources/img/streaming_sprite/005-amazon-prime-video-logo.svg",
		columnSettings: { fill: colors.next() }
	}, {
		servicio: "Start tv",
		visits: 354,
		icon: "./resources/img/streaming_sprite/006-star.svg",
		columnSettings: { fill: colors.next() }
	}, {
		servicio: "F1",
		visits: 284,
		icon: "./resources/img/streaming_sprite/007-f1.svg",
		columnSettings: { fill: colors.next() }
	}];


	// Create axes
	// https://www.amcharts.com/docs/v5/charts/xy-chart/axes/
	var xAxis = chart.xAxes.push(am5xy.CategoryAxis.new(root, {
		categoryField: "servicio",
		renderer: am5xy.AxisRendererX.new(root, {
			minGridDistance: 30
		}),
		bullet: function(root, axis, dataItem) {
			return am5xy.AxisBullet.new(root, {
				location: 0.5,
				sprite: am5.Picture.new(root, {
					width: 24,
					height: 24,
					centerY: am5.p50,
					centerX: am5.p50,
					src: dataItem.dataContext.icon
				})
			});
		}
	}));

	xAxis.get("renderer").labels.template.setAll({
		paddingTop: 20
	});

	xAxis.data.setAll(data);

	var yAxis = chart.yAxes.push(am5xy.ValueAxis.new(root, {
		renderer: am5xy.AxisRendererY.new(root, {})
	}));


	// Add series
	// https://www.amcharts.com/docs/v5/charts/xy-chart/series/
	var series = chart.series.push(am5xy.ColumnSeries.new(root, {
		xAxis: xAxis,
		yAxis: yAxis,
		valueYField: "visits",
		categoryXField: "servicio"
	}));

	series.columns.template.setAll({
		tooltipText: "{categoryX}: {valueY}",
		tooltipY: 0,
		strokeOpacity: 0,
		templateField: "columnSettings"
	});

	series.data.setAll(data);


	// Make stuff animate on load
	// https://www.amcharts.com/docs/v5/concepts/animations/
	series.appear();
	chart.appear(1000, 100);

}); // end am5.ready()