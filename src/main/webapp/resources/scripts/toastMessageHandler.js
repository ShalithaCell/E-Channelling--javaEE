/**
 *
 * @param MessageType
 * @param Message
 * @param Title
 * @constructor
 *
 * Handle all jquery toast messages
 *
 * @author shalithasenanayaka on 2019-08-30 using IntelliJ IDEA
 */
function StartToasterMessage(MessageType,Message,Title) {
    $.toaster({ message : Message, title : Title, priority : MessageType });
}