import QtQuick 2.0
import QtGraphicalEffects 1.0

Item {
    property var name: "TEXT"
    property var handleClick: (function() {console.log("test")})

    width:  rect.width;
    height: rect.height;
    Item {
        id: container;
        anchors.centerIn: parent;
        width:  rect.width  + (2 * rectShadow.radius);
        height: rect.height + (2 * rectShadow.radius);

        Rectangle {
            id: rect
            width: 152;
            height: 152;
            color: "white";
            radius: 2;
            antialiasing: true;
            anchors.centerIn: parent;
            Rectangle {
                id: nameRect
                width: parent.width;
                height: 56;
                color: "#E4F7E5";
                antialiasing: true;
                clip: true
                anchors.bottom: parent.bottom;
                Text {
                    text: name
                    color: "#DE000000"
                    verticalAlignment: Text.AlignTop
                    wrapMode: Text.WrapAnywhere
                    maximumLineCount: 2
                    elide: Text.ElideRight
                    anchors
                    {
                        leftMargin: 11
                        topMargin: 5
                        fill: parent
                        centerIn: parent
                    }
                    font {
                        family: "Roboto"
                        pixelSize: 16
                    }
                }
            }
            Rectangle
            {
                anchors{
                    left: parent.left
                    right: parent.right
                    top: parent.top
                    bottom: nameRect.top
                }
                clip: true
                Text {
                    color: "#8d9195"
                    anchors
                    {
                        fill: parent
                        centerIn: parent
                    }
                    width: parent.width
                    horizontalAlignment: Text.AlignHCenter
                    verticalAlignment: Text.AlignVCenter
                    font {
                        family: "Roboto"
                        pixelSize: 56
                    }
                    text: name.substring(0, 2)

                }

            }

            MouseArea
            {
                id: mouseArea
                anchors.fill: parent
                onPressed: rectShadow.radius = 4
                onReleased: rectShadow.radius = 8
            }
            Component.onCompleted: {
                mouseArea.clicked.connect(handleClick)
            }
        }
    }
    DropShadow {
        id: rectShadow;
        anchors.fill: source
        cached: true;
        radius: 8.0;
        samples: 16;
        color: "#80000000";
        smooth: true;
        source: container;
    }
}
