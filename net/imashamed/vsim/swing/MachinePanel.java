package net.imashamed.vsim.swing;/*
 *  This file is part of vending-simulator.
 *
 *  vending-simulator is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  vending-simulator is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with vending-simulator.  If not, see <http://www.gnu.org/licenses/>.
 */

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

/**
 * @author nathan
 *         created on 2017-02-20.
 */
public class MachinePanel extends JPanel {
    Image img;

    public MachinePanel() {
        try {
            img = ImageIO.read(new URL("http://i.imgur.com/5laqvFF.png"));
        } catch (IOException e) {
            e.printStackTrace();
            img = null;
        }
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder("Vending Machine"));
    }

    @Override
    public void paintComponent(Graphics g) {
        if (img != null) {
            g.drawImage(img, 4, 25, null);
        }
    }
}
