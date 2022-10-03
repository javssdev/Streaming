am5.ready(function() {

// Create root element
// https://www.amcharts.com/docs/v5/getting-started/#Root_element
var root = am5.Root.new("controlChartdiv");

// Set themes
// https://www.amcharts.com/docs/v5/concepts/themes/
root.setThemes([
  am5themes_Animated.new(root)
]);

// Create chart
// https://www.amcharts.com/docs/v5/charts/xy-chart/
var chart = root.container.children.push(am5xy.XYChart.new(root, {
  panX: true,
  panY: true,
  wheelX: "panX",
  wheelY: "zoomX",
  pinchZoomX:true
}));

// Create axes
// https://www.amcharts.com/docs/v5/charts/xy-chart/axes/
var xRenderer = am5xy.AxisRendererX.new(root, {});
xRenderer.grid.template.set("forceHidden", true);
xRenderer.labels.template.setAll({multiLocation: 0, location:0});

	var xAxis = chart.xAxes.push(am5xy.DateAxis.new(root, {
		categoryField: "fecha",
		baseInterval: { timeUnit: "minute", count: 30 },
		renderer: xRenderer,
		tooltip: am5.Tooltip.new(root, {}),
		extraMin: 0.01,
		extraMax: 0.01,
		tooltipLocation: 0
	}));

var yRenderer = am5xy.AxisRendererY.new(root, {});
yRenderer.grid.template.set("forceHidden", true);

var yAxis = chart.yAxes.push(am5xy.ValueAxis.new(root, {
  renderer: yRenderer
}));

// Add cursor
// https://www.amcharts.com/docs/v5/charts/xy-chart/cursor/
var cursor = chart.set("cursor", am5xy.XYCursor.new(root, {
  behavior: "none",
  xAxis: xAxis
}));
cursor.lineY.set("visible", false);

// Add series
// https://www.amcharts.com/docs/v5/charts/xy-chart/series/
var series = chart.series.push(am5xy.LineSeries.new(root, {
  name: "Series",
  xAxis: xAxis,
  yAxis: yAxis,
  valueYField: "value",
  valueXField: "timestamp",
  categoryXField: "fecha",
  locationX: 0,
  seriesTooltipTarget: "bullet",
  tooltip: am5.Tooltip.new(root, {
    labelText: "{valueY}"
  })
}));

series.bullets.push(function() {
  var circleTemplate = am5.Template.new({
    radius: 6,
    templateField: "bulletSettings",
    fill: series.get("fill"),
    strokeWidth: 2,
    stroke: root.interfaceColors.get("background")
  })

  var circle = am5.Circle.new(root, {}, circleTemplate);

  return am5.Bullet.new(root, {
    sprite: circle,
    locationX: 0
  });
});

function createGuide(value, text, dashArray) {
  var guideDataItem = yAxis.makeDataItem({ value: value });
  yAxis.createAxisRange(guideDataItem);
  guideDataItem.get("grid").setAll({
    forceHidden: false,
    strokeOpacity: 0.2,
    strokeDasharray: dashArray
  });

  var label = guideDataItem.get("label");
  label.setAll({
    text: text,
    isMeasured: false,
    centerY: am5.p100
  });

  label.adapters.add("x", function(x) {
    return chart.plotContainer.width();
  })
  
  chart.events.on("boundschanged", function(){
    label.set("x", label.get("x"))
  })  
}


createGuide(98.8, "LCL", [2, 2]);
createGuide(100.1, "CL");
createGuide(101.2, "UCL", [2, 2]);


var data = [{
  "timestamp": new Date(2020, 0, 1, 22, 30).getTime(),
  "value": 22632,
  "fecha": "Enero"
}, {
  "timestamp": new Date(2020, 1, 2, 23, 0).getTime(),
  "value": 21221,
  "fecha": "Enero"
}, {
  "timestamp": new Date(2020, 2, 3, 23, 30).getTime(),
  "value": 10120,
  "fecha": "Enero"
}, {
  "timestamp": new Date(2020, 3, 4, 0, 0).getTime(),
  "value": 12620,
  "fecha": "Enero"
}, {
  "timestamp": new Date(2020, 4, 5, 0, 30).getTime(),
  "value": 20280
}, {
  "timestamp": new Date(2020, 5, 6, 1, 0).getTime(),
  "value": 19980
}, {
  "timestamp": new Date(2020, 6, 7, 1, 30).getTime(),
  "value": 19123
}, {
  "timestamp": new Date(2020, 7, 8, 2, 0).getTime(),
  "value": 17633
}, {
  "timestamp": new Date(2020, 8, 9, 2, 30).getTime(),
  "value": 18012
}, {
  "timestamp": new Date(2020, 9, 10, 3, 0).getTime(),
  "value": 15821
}, {
  "timestamp": new Date(2020, 10, 11, 3, 30).getTime(),
  "value": 8992
}, {
  "timestamp": new Date(2020, 11, 12, 4, 0).getTime(),
  "value": 9890,
  "bulletSettings": { fill: am5.color("#f0c803") }
}];

series.data.setAll(data);

// Make stuff animate on load
// https://www.amcharts.com/docs/v5/concepts/animations/
series.appear(1000);
chart.appear(1000, 100);

}); // end am5.ready()