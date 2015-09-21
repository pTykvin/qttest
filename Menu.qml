import QtQuick 2.0
import QtGraphicalEffects 1.0
import "."

Item {
    Rectangle
    {
        color: "#eeeeee"
        anchors.fill: parent
    }
    Column
    {
        anchors.left: parent.left
        anchors.right: parent.right
        spacing: 16
        Column
        {
            anchors.left: parent.left
            anchors.right: parent.right
            Rectangle
            {
                height: 24;
                color: "#37823A"
                width: parent.width
            }
            Rectangle
            {
                Row
                {
                    Image {
                        id: menuImage
                        source: "images/menu.png"
                        fillMode: Image.Pad
                        ColorOverlay
                        {
                            anchors.fill: menuImage
                            source: menuImage
                            color: "#37823A"
                        }
                        MouseArea
                        {
                            anchors.fill: parent
                            onClicked: {
                                slideRight.start()
                                backMouseArea.enabled = true
                            }
                        }
                    }
                }

                id: head
                height: 56;
                color: "#4CAF50"
                width: parent.width
            }
        }
        Grid {
            anchors.margins: 16
            columns: 5
            rows: 4
            spacing: 16

            Repeater
            {
                model: 20
                Tile{
                    name: "Булочка сладкая вкусная с длинным названием"
                    handleClick: (function() {
                        slideRight.start()
                        backMouseArea.enabled = true
                    })
                }
            }

        }
    }
    property var frameCount: 0;
    Item {
        id: container;
        width:  360

        anchors.top: parent.top
        anchors.bottom: parent.bottom

        Rectangle {
            id: rect

            width: container.width  - (2 * rectShadow.radius);
            height: container.height
            x: -container.width
            color: "white";
            antialiasing: true;

            onXChanged: {
                frameCount++
            }
            Image {
                source: "images/menu_header.png"
            }
        }
    }
    Rectangle
    {
        id: back
        color: "transparent"
        anchors.fill: parent
        MouseArea
        {
            id: backMouseArea
            anchors.fill: parent
            enabled: false
            onClicked: {
                slideLeft.start()
                backMouseArea.enabled = false
            }
        }

    }

    ParallelAnimation {
        id: slideRight
        running: false
        NumberAnimation {
            target: rect
            property: "x"
            to: 0
            duration: 500
        }

        ColorAnimation {
            target: back
            property: "color"
            to: "#80000000"
            duration: 500
        }
    }

    ParallelAnimation {
        id: slideLeft
        running: false
        NumberAnimation {
            target: rect
            property: "x"
            to: -container.width
            duration: 500
        }
        ColorAnimation {
            target: back
            property: "color"
            to: "transparent"
            duration: 500
        }
    }

    Timer {
        interval: 1000; running: true; repeat: true
        onTriggered: {
            fps.text = "FPS: " + frameCount
            frameCount = 0
        }
    }
    DropShadow {
        id: rectShadow;
        anchors.fill: source
        cached: true;
        horizontalOffset: 3;
        radius: 8.0;
        samples: 16;
        color: "#80000000";
        smooth: true;
        source: container;
    }
    Text {
        id: fps
        font.family: "Roboto"
        font.pointSize: 100
        anchors.top: parent.top
        anchors.right: parent.right
        color: "#80000000"
    }
}

