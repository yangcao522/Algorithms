package Cruise;

//# What we expect
//        # ==============
//        # Clean, readable, testable code.
//        #
//        # The Problem
//        # ===========
//        # You will be implementing a library for displaying text
//        # boxes.
//        #
//        # We will start with basic functionality and then we will
//        # ask you to add two more complex features to the library
//        #
//
//
//        # The textboxes library should implement the following API:
//        #
//        #    TextBox box = new TextBox("hello world", '+')
//        #    box.show()
//        #    +++++++++++++++
//        #    + hello world +
//        #    +++++++++++++++
//        #
//        # By default, text is padded with one space on each side.
//        #
//
//
//
//        # Please implement the following TextBox padding API
//        #
//        # paddedRight()
//        # =============
//        # Add support for a paddedRight() method on the TextBox
//        # class that works as follows:
//        #
//        #    box = TextBox("hello", "+")
//        #    box.paddedRight(4).show()
//        #    +++++++++++++
//        #    + hello     +
//        #    +++++++++++++
//        #    box.show()
//        #    +++++++++
//        #    + hello +
//        #    +++++++++
//        #
//        #
//        # paddedBelow()
//        # =============
//        # Add support for a paddedBelow() method that works as shown
//        # below:
//        #
//        #    box = TextBox("hello", "+")
//        #    box.paddedBelow(3).show()
//        #    +++++++++
//        #    + hello +
//        #    +       +
//        #    +       +
//        #    +       +
//        #    +++++++++
//        #
//        # Calling paddedRight() or paddedBel‍‌‌‌‌‍‍‍‌‍‌‌‍‌‌‌‍‌‌ow() should not mutate
//        # the original box object.





interface Box {
    public void show();
    public Box paddedRight(int offset);
    public Box paddedBelow(int offset);
}

public class TextBox implements Box {
    private String text;
    private String sign;
    private int right;
    private int below;

    public TextBox(String text, String sign) {
        this.text = text;
        this.sign = sign;
    }

    @Override
    public void show() {
        int len = right == 0 ? text.length() + 4 : text.length() + 4 + right;
        for (int i = 0; i < len; i ++) System.out.print(sign);
        System.out.println();
        StringBuilder sb1 = new StringBuilder(text);
        for (int i = 0; i < right; i ++) sb1.append(" ");
        System.out.println(sign + " " + sb1.toString() + " " + sign);
        StringBuilder sb2 = new StringBuilder(sign);
        for (int i = 0; i < len - 2; i ++) sb2.append(" ");
        sb2.append(sign);
        for (int i = 0; i < below; i++) System.out.println(sb2.toString());
        for (int i = 0; i < len; i ++) System.out.print(sign);
        System.out.println();
    }

    @Override
    public Box paddedRight(int offset) {
        TextBox box = new TextBox(text, sign);
        box.right = offset;
        return box;
    }

    @Override
    public Box paddedBelow(int offset) {
        TextBox box = new TextBox(text, sign);
        box.below = offset;
        return box;
    }

    public static void main(String[] args) {
        TextBox box = new TextBox("Hello World", "+");
        box.show();
        box.paddedRight(6).show();
        box.paddedBelow(6).show();
    }
}
