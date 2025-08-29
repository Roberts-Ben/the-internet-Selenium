function simulateDragDrop(sourceNode, destinationNode)
{
    const dataTransfer = new DataTransfer();

    const dragStartEvent = new DragEvent('dragstart',
    {
        bubbles: true,
        cancelable: true,
        dataTransfer: dataTransfer
    });
    sourceNode.dispatchEvent(dragStartEvent);

    const dropEvent = new DragEvent('drop',
    {
        bubbles: true,
        cancelable: true,
        dataTransfer: dataTransfer
    });
    destinationNode.dispatchEvent(dropEvent);

    const dragEndEvent = new DragEvent('dragend',
    {
        bubbles: true,
        cancelable: true,
        dataTransfer: dataTransfer
    });
    sourceNode.dispatchEvent(dragEndEvent);
}
simulateDragDrop(arguments[0], arguments[1]);